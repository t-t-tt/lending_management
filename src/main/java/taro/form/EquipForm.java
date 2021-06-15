package taro.form;

import java.sql.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * イベント情報のFormクラスです
 */
@Data
public class EquipForm {
	// 資産番号
	@NotBlank
	private String asset;
	// メーカー
	@NotBlank
	private String maker;
	// OS
	@NotBlank
	private String os;
	// メモリ
	@NotBlank
	private String memory;
	// 容量
	@NotBlank
	private String capacity;
	// グラフィックボード
	private String hasGraphicBoard;
	// 保管場所
	@NotBlank
	private String storageLocation;
	// 故障
	private String isBroken;
	// リース開始日
	@NotNull
	private Date leaseStart;
	// リース期限日
	@NotNull
	private Date leaseEnd;
	// 備考
	private String remarks;
	// 棚卸日
	private Date inventoryDate;
}