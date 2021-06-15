package taro.form;

import java.sql.Date;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * イベント情報のFormクラスです
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
	@NotNull
	private Date lendStart;
	// リース期限日
	@NotNull
	private Date lendEnd;
	// 備考
	private String remarks;
}