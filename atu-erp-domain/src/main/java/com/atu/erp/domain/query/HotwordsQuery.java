package com.atu.erp.domain.query;

import com.atu.erp.domain.common.BaseSearchForMysqlVo;

import java.io.Serializable;

public class HotwordsQuery extends BaseSearchForMysqlVo
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private Integer id;
  private String hotwordName;
  private Integer sortNum;
  private Integer yn;

  public Integer getId()
  {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getHotwordName() {
    return this.hotwordName;
  }

  public void setHotwordName(String hotwordName) {
    this.hotwordName = hotwordName;
  }

  public Integer getSortNum() {
    return this.sortNum;
  }

  public void setSortNum(Integer sortNum) {
    this.sortNum = sortNum;
  }

  public Integer getYn() {
    return this.yn;
  }

  public void setYn(Integer yn) {
    this.yn = yn;
  }
}