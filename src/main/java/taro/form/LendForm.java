package taro.form;

import java.sql.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * イベント情報のFormクラスです
 */
@Data
public class LendForm {
	// 資産番号
	@NotBlank
	private String asset;
	// 社員番号
	@NotBlank
	private String employeeNumber;
	// リース開始日
	@NotNull
	private Date lendStart;
	// リース期限日
	@NotNull
	private Date lendEnd;
	// 備考
	private String remarks;
}