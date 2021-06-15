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
 * lendテーブルのEntityクラスです
 */
@Data
@Entity
@Table(name = "lend_table")
public class LendEntity {
	// 貸出管理ID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	// 機器ID
	@Column(name = "equip_id")
	private Integer equipId;
	// ユーザーID
	@Column(name = "user_id")
	private Integer userId;
	// 貸出日
	@Column(name = "lend_start")
	private Date lendStart;
	// 返却日
	@Column(name = "lend_end")
	private Date lendEnd;
	// 備考
	@Column(name = "remarks")
	private String remarks;
	// 登録日
	@Column(name = "registration_date")
	private Date registrationDate;
	// 更新日
	@Column(name = "update_date")
	private Date updateDate;
}