package taro.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * equipテーブルのEntityクラスです
 */
@Data
@Entity
@Table(name = "equip_table")
public class EquipEntity {
	// 機器ID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	// 資産番号
	@Column(name = "asset")
	private String asset;
	// メーカー
	@Column(name = "maker")
	private String maker;
	// OS
	@Column(name = "os")
	private String os;
	// メモリ
	@Column(name = "memory")
	private String memory;
	// 容量
	@Column(name = "capacity")
	private String capacity;
	// グラフィックボード
	@Column(name = "has_graphic_board")
	private Boolean hasGraphicBoard;
	// 保管場所
	@Column(name = "storage_location")
	private String storageLocation;
	// 故障
	@Column(name = "is_broken")
	private Boolean isBroken;
	// リース開始日
	@Column(name = "lease_start")
	private Date leaseStart;
	// リース期限日
	@Column(name = "lease_end")
	private Date leaseEnd;
	// 備考
	@Column(name = "remarks")
	private String remarks;
	// 削除状態
	@Column(name = "is_deleted")
	private Boolean isDeleted;
	// 貸出状態
	@Column(name = "is_lent")
	private Boolean isLent;
	// 棚卸日
	@Column(name = "inventory_date")
	private Date inventoryDate;
	// 登録日
	@Column(name = "registration_date")
	private Date registrationDate;
	// 更新日
	@Column(name = "update_date")
	private Date updateDate;
}