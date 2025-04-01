
package com.zt.eweb.modular.binder.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.zt.eweb.framework.mybatis.core.data.copy.Accept;
import com.zt.eweb.framework.mybatis.core.entity.BaseEntity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author mazc@dibo.ltd
 * @version v2.0
 * @date 2019/1/30
 */
@Getter
@Setter
@Accessors(chain = true)
//@TableName("\"user\"")
public class User extends BaseEntity<String> {

  private static final long serialVersionUID = 3050761344045195972L;

  @TableField
  private String departmentId;

  @TableField
  private String username;

  @Accept(name = "itemName")
  @TableField
  private String gender;

  private LocalDate birthdate;

  private LocalDateTime localDatetime;

  @TableField("`character`")
  //@TableField
  private String character;

}
