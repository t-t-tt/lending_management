package taro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import taro.entity.UserEntity;

/**
 * Userテーブルを操作するためのRepositoryクラスです
 */
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	/**
	 * @param id
	 * @return ユーザー
	 */
	public UserEntity findOneById(Integer id);

	/**
	 * 未削除ユーザー一覧を取得.
	 * @param
	 * @return ユーザー一覧（未削除）
	 */
	public List<UserEntity> findByIsDeletedFalse();

	/**
	 * 未削除かつ未退職ユーザー一覧を取得.
	 * @param
	 * @return ユーザー一覧
	 */
	public List<UserEntity> findByIsDeletedFalseAndRetirementDateIsNull();

	/**
	 * 社員名検索.
	 * @param name
	 * @return ユーザー一覧
	 */
	public List<UserEntity> findByNameContainingAndIsDeletedFalse(String name);
}