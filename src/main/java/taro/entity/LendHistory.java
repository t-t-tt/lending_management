package taro.entity;

import lombok.Data;

/**
 * 貸出管理画面表示用クラスです
 */
@Data
public class LendHistory {
	private UserEntity user;
	private LendEntity lend;
}