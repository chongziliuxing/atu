function Map() {
	var myArrays = new Array();
	//��Ӽ�ֵ��������ظ����滻ֵ
	this.put = function(key, value) {
		var v = this.get(key);
		if (v == null) {
			var len = myArrays.length;
			myArrays[len] = {Key: key, Value: value};
		} else {
			for (var i = 0; i < myArrays.length; i++) {
				if (myArrays[i].Key == key) {
					myArrays[i].Value = value;
				}
			}
		}
	},
	//���ݼ���ȡֵ
	this.get = function(key) {
		for (var i = 0; i < myArrays.length; i++) {
			if (myArrays[i].Key == key) {
				return myArrays[i].Value;
			}
		}
		return null;
	},
	//ɾ��ĳһ��
	this.drop = function(key) {
		var dropIndex = -1;
		for (var i = 0; i < myArrays.length; i++) {
			if (myArrays[i].Key == key) {
				dropIndex = i;
			}
        }
            if (dropIndex == -1) {
                return;
            }
            for (var i = dropIndex; i < myArrays.length - 1; i++) {
                myArrays[i] = myArrays[i + 1];
            }
            myArrays.length--;
        },
        //��split�ָ�������ֵ�ַ���
        this.toString = function(split) {
            var str = "";
            if (myArrays.length > 1) {
                for (var i = 0; i < myArrays.length - 1; i++) {
                    str += myArrays[i].Value + split;
                }
                if (str != "") {
                    str += myArrays[myArrays.length - 1].Value;
                }
            } else if (myArrays.length == 1) {
                str = myArrays[0].Value;
            }
            return str;
        },
        //��json��ʽ���ؼ�ֵ���ַ�����Ӧ���json.jsʹ��
        this.toJSONString = function() {
            var json = (myArrays.length > 0) ? '{"myArray":[' : "";
            for (var i = 0; i < myArrays.length; i++) {
                json += '{"Key":"' + myArrays[i].Key + '","Value":"' + myArrays[i].Value + '"},';
            }
            if (myArrays.length > 0) {
                json = json.substring(0, json.length - 1);
                json += ']}';
            }
            return json;
        },
        //��������ʽ���ؼ��б�
        this.getKeySets = function() {
            var newArray = new Array();
            for (var i = 0; i < myArrays.length; i++) {
                newArray[i] = myArrays[i].Key;
            }
            return newArray;
        },
        //��������ʽ����ֵ�б�
        this.getValues = function() {
            var newArray = new Array();
            for (var i = 0; i < myArrays.length; i++) {
                newArray[i] = myArrays[i].Value;
            }
            return newArray;
        }
}