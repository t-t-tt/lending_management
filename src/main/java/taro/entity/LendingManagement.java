package taro.entity;

import lombok.Data;

/**
 * participantテーブルのEntityクラスです
 */
@Data
public class LendingManagement {
	private EquipEntity equip;
	private UserEntity user;
	private LendEntity lend;
	private Integer count;
}