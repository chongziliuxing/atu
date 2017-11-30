var chatMap = new Map();
var allIntervalId, oneIntervalId;

String.prototype.a2b = function() {
    var A = arguments;
    return this.replace(/\{(\d+)\}/g, function(a, b) {
        return A[b - 1] === void (0) ? '' : A[b - 1]
    });
}

var tmpKey = "{1}_{2}";
/**
 * ʵ��һ���µ�Options
 *
 * @param id
 * @param onloadFunc
 */
function createOptions(id, onloadFunc) {
    var newOptions = {
        chart : {
            renderTo : id,
            type : 'spline',
            marginRight : 10,
            events : {
                load : function() {
                    if (onloadFunc != undefined
                            && typeof onloadFunc == "function") {
                        setInterval(onloadFunc(), 30000);
                    }
                }
            }
        },
        rangeSelector : {
            enabled : false,
            inputEnabled : false,
            selected : 0
        },
        title : {
            text : 'Live random data',
            useHTML : true
        },
        xAxis : {
            type : 'datetime',
            tickPixelInterval : 150
        },
        yAxis : {
            min : 0,
            title : {
                text : 'Value'
            },
            plotLines : [
                {
                    value : 0,
                    width : 1,
                    color : '#808080'
                }
            ]
        },
        tooltip : {
            crosshairs : true,
            shared : true,
            xDateFormat: "ʱ��:��%H:%M"
        },
        plotOptions : {
            spline : {
                marker : {
                    radius : 4,
                    lineWidth : 0,
                    enabled: true
                }
            }
        },
        legend : {
            enabled : true
        },
        navigator : {
            top : 295
        },
        exporting : {
            enabled : true
        },
        series : []
    };
    chatMap.put(id, newOptions);
    return newOptions;
}

/**
 * ʵ��һ���µ�Options
 *
 * @param id
 * @param onloadFunc

 */
function createOptions4WeekMonth(id, title) {
    var newOptions = {
        chart : {
            renderTo : id,
            type : 'spline',
            marginRight : 10
        },
        rangeSelector : {
            enabled : false,
            inputEnabled : false,
            selected : 0
        },
        title : {
            text : 'Live random data',
            useHTML : true
        },
        xAxis : {
            type : 'datetime',
            tickPixelInterval : 150
        },
        yAxis : {
            min : 0,
            title : {
                text : 'Value'
            },
            plotLines : [
                {
                    value : 0,
                    width : 1,
                    color : '#808080'
                }
            ]
        },
        tooltip : {
            crosshairs : true,
            shared : true,
            pointFormat:"<span style=\"color:{series.color}\">{point.name}</span>: <b>{point.y}</b><br/>",
            xDateFormat: title
        },
        plotOptions : {
            spline : {
                marker : {
                    radius : 4,
                    lineWidth : 0,
                    enabled: true
                }
            }
        },
        legend : {
            enabled : true
        },
        navigator : {
            top : 295
        },
        exporting : {
            enabled : true
        },
        series : []
    };
    return newOptions;
}

/**
 * �ж��Ƿ�Ϊȫ����Options
 *
 * @param curOption

 */
function getOption(curOption) {

    return (curOption == undefined || curOption == null) ? options : curOption;
}

function topHide(bool) {

    if (bool) {
        $('#topMenu').hide();
        $('#topMenu').removeClass('top');
    } else {
        $('#topMenu').show();
        $('#topMenu').addClass('top');
    }
}

/**
 * ��ʼ��/����ͼ��
 *
 * @param name
 * @param rftype

 */
function initTable(name, rftype, minute) {

    var url = getUrl(name, rftype, 'report/newReg_showCount');
    var dataResult = load(url + '&queryVO.init=1');
    if (dataResult == null) {
        alert('���ݼ��ش���');
        return;
    }
    hideDiv(true);
    initOthers();
    parameter.put('name', name);
    parameter.put('minute', minute);
    parameter.put('rftype', rftype);
    parameter.put('showDetail', '1');
    options.series = [];
    pushData(dataResult, true);
    topHide(false);
    clearAllInterval();
        resetMinutes(dataResult.minute);
    oneIntervalId = setInterval(oneTypeNext, 30000);
}

/**
 * ��ʼ��/����ͼ��
 *
 * @param name
 * @param rftype

 */
function initTableNew(dataArr) {

    hideDiv(true);
    initOthers();
    parameter.put('name', dataArr.name);
    parameter.put('minute', dataArr.minute);
    parameter.put('rftype', dataArr.rftype);
    parameter.put('showDetail', '1');

    options.series = [];
    pushData(dataArr, true);
    topHide(false);

    resetMinutes(dataArr.minute);
    clearAllInterval();
    oneIntervalId = setInterval(oneTypeNext, 30000);
}

/**
 * ��ʼ�����в���, ���µĲ�ѯ��������
 *

 */
function initOthers() {
    parameter = new Map();
    dayMap = new Map();
    lastMinutes = '';
    time = 0;
    tableName = '';
}

/**
 * ����ǰͼ������ʵ��
 *
 * @param data
 * @param isCreate
 * @param curOptions

 */
function pushData(data, isCreate, curOptions) {

    var isOne = (parameter.get('showDetail') == '1');
    var reversed = false;
    var tmpOptions = getOption(curOptions);
    if (tmpOptions.series == null && isOne) {
        tmpOptions = chart.options;
    }
    // ���������, ֻ�г�ʼ�� ����ˢ�µ�ʱ�� �Ż���
    if (isNotNull(data.todayList)) {
        addSeries(data.todayList, true, tmpOptions);
    }

    // ����ָ�����ڻ����ǳ�ʼ��ʱ ��������ݼ���
    if (isNotNull(data.yesterdayList)) {

        addSeries(data.yesterdayList, false, tmpOptions);
    }

    // ����ָ�����ڻ����ǳ�ʼ��ʱ ��������ݼ���
    if (isNotNull(data.weekList)) {
        addSeries(data.weekList, false, tmpOptions);
    }

    // ���ܻ��� ������, ֻ�г�ʼ�� ����ˢ�µ�ʱ�� �Ż���
    if (isNotNull(data.weekMonthList)) {
        reversed = true;
        addWeekMonthSeries(data.weekMonthList, true, tmpOptions);
    }

    // ����ָ�����ڻ����ǳ�ʼ��ʱ ���ܻ����µ����ݼ���
    if (isNotNull(data.otherWeekMonthList)) {
        reversed = true;
        addWeekMonthSeries(data.otherWeekMonthList, false, tmpOptions);
    }

    clearWeekMonth(reversed);
    lastMinutes = data.lastMinute;
    // if(lastSeries != null) {
    // tmpOptions.series.push(lastSeries);
    // }
    Highcharts.setOptions({
        global: {
            useUTC: false
        }
    });
    tmpOptions.title.text = "<div style='border:1px solid #ccc; display:block; background:rgb(19,130,185); color:white;font-weight:bold;width:200px; height:25px;'><a style='cursor:pointer;' onclick=\"javascript:initTable('"
            + data.name
            + "','"
            + data.rftype
            + "','"
            + data.minute
            + "');\">"
            + transMap.get(data.rftype) + "</a></div>";

    if (isCreate != undefined && isCreate) {

        chart = isOne ? new Highcharts.StockChart(tmpOptions)
                : new Highcharts.Chart(tmpOptions);

        if (isOne) {

            put('chart', chart);
            put('options', tmpOptions);
        }
        chatMap.put(data.rftype, chart);
    }
}

/**
 * ����չ��
 * @param year
 * @param week

 */
function initWeek(year, week) {
    var url = 'report/newReg_searchWeek.action?queryVO.rftype=' + parameter.get('rftype');
    var init = isNotNull(year) && isNotNull(week);

    //������ǳ�ʼ��, ��ƴ��Ҫ��ӵ���, ��
    if (init) {
        url += "&queryVO.year=" + year + "&queryVO.week=" + week;
    }
    clearWeekMonth(0);

    var result = load(url);
    if (options2 != undefined && options2.series != undefined) {
        options2.series = [];
    }
    options2 = createOptions4WeekMonth('container', '���ܻ���');
    pushData(result, !init, options2);

    //��ǰĬ�ϵ�Ϊ ��һ��
    putFlag(queryWeekMap, result.year, result.week);
    //���ܵ���Ҫ����һ�ܵĻ����ϼ�1
    putFlag(queryWeekMap, result.year, parseInt(result.week) + 1);
}

function clearWeekMonth(bool) {

    if (!bool) {

        parameter.drop('week_month');
        queryWeekMap = new Map();
        queryMonthMap = new Map();
    }
}
/**
 * ����չ��
 * @param year
 * @param month

 */
function initMonth(year, month) {
    var url = 'report/newReg_searchMonth.action?queryVO.rftype=' + parameter.get('rftype');

    var init = isNotNull(year) && isNotNull(month);
    if (init) {
        url += "&queryVO.year=" + year + "&queryVO.month=" + month;
    }
    clearWeekMonth(0);

    parameter.put('week_month', "���»���");
    var result = load(url);
    if (options2 != undefined && options2.series != undefined) {
        options2.series = [];
    }
    options2 = createOptions4WeekMonth('container', '���»���');
    pushData(result, !init, options2);

    //��ǰĬ�ϵ�Ϊ ��һ��
    putFlag(queryMonthMap, result.year, result.month);
    //���µ���Ҫ����һ�µĻ����ϼ�1
    putFlag(queryMonthMap, result.year, parseInt(result.month) + 1);
}

/**
 * ������ع��ı��
 * @param maps
 * @param year
 * @param monthOrWeek

 */
function putFlag(maps, year, monthOrWeek) {
    maps.put(tmpKey.a2b(year, monthOrWeek), "1");
}

/**
 * ����, ���µ�Series
 * @param dataArray
 * @param bool
 * @param curOptions

 */
function addWeekMonthSeries(dataArray, bool, curOptions) {
    var series = createWeekMonthSeries(dataArray, bool);

    var tmpOptions = getOption(curOptions);

    tmpOptions.series.push(series);
}

/**
 * ����, ���� ����ָ�����ݼ��ϵ�Series
 *
 * @param dataArray
 * @param bool

 */
function createWeekMonthSeries(dataArray, bool) {
    var innerTime;
    var series = {
        data : []
    };
    for (var i = 0; i < dataArray.length; i++) {

        var obj = dataArray[i];

        if (i == 0) {
            var innerDate = new Date();
            innerTime = innerDate.getTime() - (innerDate.getHours() * 3600 + innerDate.getMinutes() * 60 + innerDate.getSeconds()) * 1000 - innerDate.getMilliseconds();
            if (bool != undefined && bool) {
                //					innerTime = Date.parse(obj.date);
            }
            series.name = obj.date + ' �� ' + dataArray[dataArray.length - 1].date;
        }


        tableName = obj.date;
        series.data.push({
            x : innerTime + obj.minutes * 24 * 60 * 60 * 1000,
            y : obj.count,
            name : obj.date
        });
    }
    return series;
}
/**
 * �����ݼ���Series Push��options.series
 *
 * @param dataArray
 * @param bool
 * @param curOptions
 */
function addSeries(dataArray, bool, curOptions) {


    var series = createSeries(dataArray, bool);

    // series.name = tableName;

    var tmpOptions = getOption(curOptions);

    tmpOptions.series.push(series);
    dayMap.put(tableName, tableName);
}

/**
 * ����ָ�����ݼ��ϵ�Series
 *
 * @param dataArray
 * @param bool

 */
function createSeries(dataArray, bool) {
    var series = {
        data : []
    };
    var temp = 10;
    if (parameter.get("minute") != undefined && parameter.get("minute") != null)
        temp = parameter.get("minute");
    for (var i = 0; i < dataArray.length; i++) {


        var obj = dataArray[i];

        if (i == 0) {

            if (bool != undefined && bool) {
                //				time = Date.parse(obj.date);
                date = new Date();
                time = date.getTime() - (date.getHours() * 3600 + date.getMinutes() * 60 + date.getSeconds()) * 1000 - date.getMilliseconds();
                //				alert(new Date().getTime());
                //				time=time+new Date().getTimezoneOffset()*60000;
            }

            tableName = obj.date;
            series.name = tableName;

        }

        series.data.push({

            //x : time + (obj.minutes - temp) * 1000 * 60,
            x : time + (obj.minutes) * 1000 * 60,
            y : obj.count
        });
    }
    return series;
}
function createSeriesWzf(dataArray, series, bool) {
    // var series = {
    // data : []
    // };
    // var chart=series.chart;
    var count = parameter.get("count");
    var data = series.data;
    for (var i = 0; i < dataArray.length; i++) {
        var shift = (data.length >= (i + 1));
        var redraw = ((i + 1) == dataArray.length);
        // alert(shift);
        var obj = dataArray[i];
        if (i == 0) {
            if (bool != undefined && bool) {
                time = Date.parse(obj.date);
            }
            tableName = obj.date;
        }
        var x = time + obj.minutes * 1000 * 60;
        var y = obj.count;
        // series.data.push( {
        // x : time + obj.minutes * 1000 * 60,
        // y : obj.count
        // });
        series.addPoint([ x, y ], redraw, shift);
    }
    return series;
}
/**
 * �ǿ�
 *
 * @param arr

 */
function isNotNull(arr) {
    return arr != undefined && arr != null && arr.length > 0;
}

/**
 * ajax load����
 *
 * @param url

 */
function load(url) {
    var result = null;
    var append = url.indexOf('?') == -1 ? '?' : '&';
    $.ajax({
        url : url + append + 't=' + new Date().getTime(),
        type : "POST",
        dataType : "json",
        async : false,
        beforeSend : function() {
        },
        error : function(XMLHttpRequest) {

        },
        success : function(data, textStatus) {
            if (data != null) {

                result = data.perReportData;
            }
        },
        complete : function(XMLHttpRequest, textStatus) {
        }
    });
    return result;
}

function hideAll() {
    $('#weekTR').hide();
    $('#monthTR').hide();
    $('#dayTR').hide();
}

/**
 * ָ�������� ���»�ȡ����
 *
 * @param mins

 */
function changeMinute(mins) {

    hideAll();
    if (mins.indexOf("_") != -1) {

        if (mins.indexOf("week") != -1) {
            $('#weekTR').show();
            initWeek();
        }
        if (mins.indexOf("month") != -1) {
            $('#monthTR').show();
            initMonth();
        }
        clearAllInterval();
        resetMinutes(mins);
        return;
    }
    $('#dayTR').show();
    var count = 30;
    if (mins == '5') {
        count = mins * 30;
    }
    if (mins == '10') {
        count = mins * 30;
    }
    if (mins == '30') {
        count = mins * 20;
    }
    if (mins == '60') {
        count = mins * 10;
    }
    parameter.put('count', count);
    parameter.put('minute', mins);

    resetMinutes(mins);
    refresh();
}

/**
 * ��ȡָ������������
 * @param min

 */
function addWeek(needValid) {

    var year = $('#weekYear').val();
    var week = $('#week').val();

    var key = tmpKey.a2b(year, week);

    if (needValid && queryWeekMap.get(key) != null) {
        alert(year + '��  ��' + week + '��  �����Ѿ���ӹ�, �����ظ����!');
        return;
    }
    clearAllInterval();
    var url = 'report/newReg_ajaxWeek.action?queryVO.year=' + year + '&queryVO.week=' + week + '&queryVO.rftype=' + parameter.get('rftype');
    var dataResult = load(url);

    if (dataResult == null || !isNotNull(dataResult.otherWeekMonthList)) {
        alert("û�л�ȡ��  " + year + "�� ��" + week + "�� ������!");
        return;
    }
    pushData(dataResult, true, options2);
    putFlag(queryWeekMap, year, week);
}

/**
 * ��ȡָ���·ݵ�����
 * @param min

 */
function addMonth(needValid) {

    var year = $('#monthYear').val();
    var month = $('#month').val();

    var key = tmpKey.a2b(year, month);

    if (needValid && queryMonthMap.get(key) != null) {
        alert(year + '��  ' + month + '��  �����Ѿ���ӹ�, �����ظ����!');
        return;
    }
    clearAllInterval();
    var url = 'report/newReg_ajaxMonth.action?queryVO.year=' + year + '&queryVO.month=' + month + '&queryVO.rftype=' + parameter.get('rftype');
    var dataResult = load(url);

    if (dataResult == null || !isNotNull(dataResult.otherWeekMonthList)) {
        alert("û�л�ȡ��  " + year + "�� " + month + "�� ������!");
        return;
    }
    pushData(dataResult, true, options2);
    putFlag(queryMonthMap, year, month);
}

/**
 * ����ǰѡ�еķ����� href����ɾ��, Ϊ���ɵ��״̬
 *
 * @param mins

 */
function resetMinutes(mins) {
    $("a[name=AMinute]").each(function() {
        var val = $(this).attr('id');
        if (val == mins) {
            $(this).removeAttr('href');
        } else {
            $(this).attr('href', 'javascript:changeMinute(\'' + val + '\')');
        }
    });
}

/**
 * ˢ�µ���ͼ��Ķ���
 *

 */
function refresh() {
    var values = dayMap.getValues();
    options.series = [];
    var isCreate = false;
    if (chart != undefined || chart != null) {
        chart.options.series = [];
    }
    for (var i = 0; i < values.length; i++) {

        if (i == values.length - 1)
            isCreate = true;

        var url = createURL('report/newReg_ajaxLoad', values[i]);

        var dataResult = load(url);
        pushData(dataResult, isCreate);
    }
    clearAllInterval();
    oneIntervalId = setInterval(oneTypeNext, 30000);
}

/**
 * ���һ�������
 *
 * @param day

 */
function addOneDay(day) {
    if (isNull(day)) {
        alert('��ѡ����ӵ�����');
        $('#searchDate').click();
        return;
    }
    if (dayMap.get(day) != null || dayMap.get(day.replace("-", "")) != null) {
        alert('�Ѿ���ӹ�[' + day + ']������, �����ظ����.');
        return;
    }
    var url = createURL('report/newReg_ajaxLoad', day);
    var dataResult = load(url);

    if (dataResult == null || !isNotNull(dataResult.yesterdayList)) {
        alert('û�в�ѯ��[' + day + ']������, ���ʧ��');
        return;
    }

    pushData(dataResult, true);
}

function isNull(obj) {

    return obj == null || obj == '' || obj.length == 0;
}

/**
 * ��ʼ������options.series = []
 *

 */
function initOptionsMap() {

    var values = allOptions.getValues();
    for (var i = 0; i < values.length; i++) {
        values[i].series = [];
    }
}

/**
 * ��ʾ����ͼ��container
 *
 * @param bool

 */
function hideDiv(bool) {

    if (bool)
        $('#container').show();
    else
        $('#container').hide();

    $('div[id^=container_]').each(function() {
        if (bool)
            $(this).hide();
        else
            $(this).show();
    });
}

/**
 * ��ʼ��ȫ��, ������topMenu
 *
 * @param type

 */
function initAllNew(dataArr) {

    hideDiv(false);
    topHide(true);

    parameter.put("minute", dataArr.minute);
    parameter.put('rftype',dataArr.rftype);
    var dataResult = dataArr; // ��ȡ���ص�����

    var arr = dataResult.dataMap;
    var others = dataResult.yesterdayMap;
    var week = dataResult.weekMap;
    var index = 0;

    for (var i in arr) {
        var cOption = null;

        cOption = createOptions('container_' + index);

        allOptions.put(i, cOption);

        dataResult.todayList = arr[i];

        if (others[i] != undefined) {
            dataResult.yesterdayList = others[i];
        }
        if (week[i] != undefined) {
            dataResult.weekList = week[i];
        }

        dataResult.rftype = i;

        pushData(dataResult, true, cOption);
        index++;
    }
    clearAllInterval();
    allIntervalId = setInterval(doAllNext, 30000);
}

/**
 * ��ȡȫ��������
 *
 */
function doAllNext() {
    var tmpResult = load('report/newReg_showAll.action?queryVO.minute=' + parameter.get('minute') + '&queryVO.lastMinute=' + lastMinutes+"&queryVO.rftype=" + parameter.get('rftype') );

    var arr = tmpResult.dataMap;
    var others = tmpResult.yesterdayMap;
    var week = tmpResult.weekMap;
    lastMinutes = tmpResult.lastMinute;
    for (var i in arr) {

        var tmpChart = chatMap.get(i);
        var tmpData = createSeries(arr[i], true);
        // createSeriesWzf(arr[i],tmpChart.series[0], true)
        tmpChart.series[0].setData(tmpData.data, true);
        if (others != 'null' && others != undefined && others[i] != undefined) {
            var otherData = createSeries(others[i], false);
            tmpChart.series[1].setData(otherData.data, true);
            // createSeriesWzf(others[i],tmpChart.series[1], false);
        }
        if (week != 'null' && week != undefined && week[i] != undefined) {
            var otherData = createSeries(week[i], false);
            tmpChart.series[2].setData(otherData.data, true);
        }
    }
}

/**
 * ���������� ����ʱ��������������
 *

 */
function oneTypeNext() {
    var tmpChart = get('chart');
    // alert('one next chart length : ' + tmpChart.series.length);
    var url = createURL('report/newReg_showOne') + '&queryVO.oneTypeDays=' + dayMap.getValues().join(",");
    if (parameter.get('minute') == '1') {
        url += '&queryVO.init=1';
    }
    var tmpResult = load(url);


    var others = tmpResult.yesterdayMap;
    lastMinutes = tmpResult.lastMinute;

    var tmpData = createSeries(tmpResult.todayList, true);
    tmpChart.series[0].setData(tmpData.data, true);

    for (var i in others) {
        var index = getSeriesIndex(i, tmpChart);
        var otherData = createSeries(others[i], false);
        tmpChart.series[index].setData(otherData.data, true);
    }
}

function getSeriesIndex(seriesName, chart) {

    for (var i = 0; i < chart.series.length; i++) {

        if (chart.series[i].name == seriesName) {
            return i;
        }
    }
}

/**
 * ����ȫ��������url
 *
 * @param actionName
 * @param date

 */
function createURL(actionName, date) {
    var day = date == null || date == undefined ? null : date;
    return getUrl(parameter.get('name'), parameter.get('rftype'), actionName,
            day, parameter.get('minute'), parameter.get('count'));
}
/**
 * ���ȫ��Interval
 *

 */
function clearAllInterval() {

    clearInterval(oneIntervalId);
    clearInterval(allIntervalId);
}

function addShowCount(counts) {

    if (isNull(counts)) {

        alert('��������ʾ�ڵ���');
        $('#showCounts').focus();
        return;
    }

    parameter.put('count', counts);
    refresh();
    $('#showCounts').val('');
}

function put(k, v) {
    parameter.put(k, v);
}

function get(k) {
    return parameter.get(k);
}

function isOne() {
    return parameter.get('showDetail') == '1';
}