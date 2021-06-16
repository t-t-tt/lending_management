package taro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import taro.entity.LendEntity;

/**
 * participantテーブルを操作するためのRepositoryクラスです
 */
public interface LendRepository extends JpaRepository<LendEntity, Integer> {
	/**
	 * 指定したIDに紐づく貸出情報を取得します.
	 * @param 貸出ID Id
	 * @return 貸出情報
	 */
	public LendEntity findOneById(Integer id);
	/**
	 * 指定したEquipIDに紐づく貸出情報を取得します.
	 * @param 機器ID equipId
	 * @return 貸出情報
	 */
	public LendEntity findByEquipId(Integer equipId);

	public LendEntity findOneByEquipIdAndIsDeletedFalse(Integer equipId);

	public List<LendEntity> findByUserIdAndIsDeletedFalse(Integer usesrId);

	/**
	 * 指定したUserIDに紐づく貸出情報を取得します.
	 * @param ユーザーID equipId
	 * @return 貸出情報リスト
	 */
	public List<LendEntity> findByUserId(Integer userId);

	/**
	 * 未削除貸出情報一覧を取得.
	 * @param
	 * @return 貸出情報一覧（未削除）
	 */
	public List<LendEntity> findAll();

	public List<LendEntity> findByIsDeletedFalse();

	public void deleteByEquipId(Integer equipId);
	public List<LendEntity> findByEquipIdAndIsDeletedTrue(Integer equipId);
}