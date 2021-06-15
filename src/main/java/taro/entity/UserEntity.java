package taro.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * event_entryテーブルのEntityクラスです
 */
@Data
@Entity
@Table(name = "user_table")
public class UserEntity {
	// ユーザーID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	// 社員番号
	@Column(name = "employee_number")
	private String employeeNumber;
	// 氏名
	@Column(name = "name")
	private String name;
	// 氏名(カタカナ)
	@Column(name = "name_kana")
	private String nameKana;
	// 所属部署
	@Column(name = "department")
	private String department;
	// 電話番号
	@Column(name = "tel_number")
	private String telNumber;
	// メールアドレス
	@Column(name = "mail")
	private String mail;
	// 年齢
	@Column(name = "age")
	private Integer age;
	// 性別
	@Column(name = "gender")
	private String gender;
	// 役職
	@Column(name = "position")
	private String position;
	// PCアカウント権限
	@Column(name = "privilege")
	private String privilege;
	// 削除状態
	@GeneratedValue
	@Column(name = "is_deleted")
	private Boolean isDeleted;
	// 退職日
	@Column(name = "retirement_date")
	private Date retirementDate;
	// 登録日
	@GeneratedValue
	@Column(name = "registration_date")
	private Date registrationDate;
	// 更新日
	@GeneratedValue
	@Column(name = "update_date")
	private Date updateDate;
}