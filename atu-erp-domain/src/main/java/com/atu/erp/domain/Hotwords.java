package com.atu.erp.domain;

import java.io.Serializable;
import java.util.Date;

public class Hotwords
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private Integer id;
  private String hotwordName;
  private Integer sortNum;
  private Integer yn;
  private Date created;
  private Date modified;

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

  public Integer getYn()
  {
    return this.yn;
  }

  public void setYn(Integer yn) {
    this.yn = yn;
  }

  public Date getCreated() {
    return this.created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public Date getModified() {
    return this.modified;
  }

  public void setModified(Date modified) {
    this.modified = modified;
  }
}