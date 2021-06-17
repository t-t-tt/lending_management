package taro.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * 貸出情報のFormクラスです
 */
@Data
public class LendForm {
	// 資産番号
	@NotNull
	private Integer equipId;
	// 社員番号
	@NotNull
	private Integer userId;
	// リース開始日
	@NotBlank
	private String lendStart;
	// リース期限日
	@NotBlank
	private String lendEnd;
	// 備考
	private String remarks;
}