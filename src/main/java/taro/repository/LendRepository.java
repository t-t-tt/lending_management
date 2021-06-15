package taro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import taro.entity.LendEntity;

/**
 * participantテーブルを操作するためのRepositoryクラスです
 */
public interface LendRepository extends JpaRepository<LendEntity, Integer> {
	/**
	 * 指定したEquipIDに紐づく機器を取得します.
	 * @param 機器ID equipId
	 * @return 機器
	 */
	public LendEntity findOneByEquipId(Integer EquipId);

	/**
	 * 未削除機器一覧を取得.
	 * @param
	 * @return 機器一覧（未削除）
	 */
	public List<LendEntity> findByIsDeletedFalse();

	public void deleteByEquipId(Integer equipId);
}