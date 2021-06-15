package taro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import taro.entity.EquipEntity;

/**
 * participantテーブルを操作するためのRepositoryクラスです
 */
public interface EquipRepository extends JpaRepository<EquipEntity, Integer> {
	/**
	 * 指定したIDに紐づく機器を取得します.
	 * @param id id
	 * @return 機器
	 */
	public EquipEntity findOneById(Integer id);

	/**
	 * 未削除機器一覧を取得.
	 * @param
	 * @return 機器一覧（未削除）
	 */
	public List<EquipEntity> findByIsDeletedFalse();

	/**
	 * 指定した資産番号に紐づく機器を取得
	 * @param
	 * @return 機器一覧（未削除）
	 */
	public EquipEntity findOneByAsset(String asset);
}