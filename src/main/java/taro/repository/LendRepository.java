package taro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import taro.entity.LendEntity;

/**
 * participantテーブルを操作するためのRepositoryクラスです
 */
public interface LendRepository extends JpaRepository<LendEntity, Integer> {

	/**
	 * @param id
	 * @return 貸出情報
	 */
	public LendEntity findOneById(Integer id);

	/**
	 * @param equipId
	 * @return 貸出情報
	 */
	public LendEntity findByEquipId(Integer equipId);

	/**
	 * @param equipId
	 * @return 貸出一覧（未削除）
	 */
	public LendEntity findOneByEquipIdAndIsDeletedFalse(Integer equipId);

	/**
	 * @param usesrId
	 * @return 貸出一覧（未削除）
	 */
	public List<LendEntity> findByUserIdAndIsDeletedFalse(Integer usesrId);

	/**
	 * @param userId
	 * @return 貸出一覧
	 */
	public List<LendEntity> findByUserId(Integer userId);


	/**
	 * @param equipId
	 * @return 貸出一覧（削除済み）
	 */
	public List<LendEntity> findByEquipIdAndIsDeletedTrue(Integer equipId);
}