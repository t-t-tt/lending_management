package taro.form;

import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * 機器情報のFormクラスです
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
	private Boolean hasGraphicBoard;
	// 保管場所
	@NotBlank
	private String storageLocation;
	// 故障
	private Boolean isBroken;
	// リース開始日
	@NotBlank
	private String leaseStart;
	// リース期限日
	@NotBlank
	private String leaseEnd;
	// 備考
	private String remarks;
	// 棚卸日
	private String inventoryDate;
}