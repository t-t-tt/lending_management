package taro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import taro.entity.EquipEntity;

/**
 * equipテーブルを操作するためのRepositoryクラスです
 */
public interface EquipRepository extends JpaRepository<EquipEntity, Integer> {

	/**
	 * @param id
	 * @return 機器情報
	 */
	public EquipEntity findOneById(Integer id);

	/**
	 * 未削除機器一覧を取得.
	 * @param
	 * @return 機器一覧（未削除）
	 */
	public List<EquipEntity> findByIsDeletedFalse();

	/**
	 * 未削除かつ未貸出機器一覧を取得.
	 * @param
	 * @return 機器一覧
	 */
	public List<EquipEntity> findByIsDeletedFalseAndIsLentFalse();

	/**
	 * 資産番号部分一致検索（未削除）.
	 * @param
	 * @return 機器一覧
	 */
	public List<EquipEntity> findByAssetContainingAndIsDeletedFalse(String asset);
}