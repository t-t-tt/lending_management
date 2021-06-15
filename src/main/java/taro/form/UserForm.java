package taro.form;

import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

import lombok.Data;

/**
 * イベント参加情報のFormクラスです
 */
@Data
public class UserForm {
	// 社員番号
	@NotBlank
	private String employeeNumber;
	// 氏名
	@NotBlank
	private String name;
	// 氏名(カタカナ)
	@NotBlank
	private String nameKana;
	// 所属部署
	@NotBlank
	private String department;
	// 電話番号
	@NotBlank
	private String telNumber;
	// メールアドレス
	@Email
	private String mail;
	// 年齢
	@NotNull
	private Integer age;
	// 性別
	@NotBlank
	private String gender;
	// 役職
	@NotBlank
	private String position;
	// PCアカウント権限
	@NotBlank
	private String privilege;

	// 退職日
	@Nullable
	private Date retirementDate;
}