package taro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import taro.entity.UserEntity;

/**
 * eventテーブルを操作するためのRepositoryクラスです
 */
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	/**
	 * 指定したIDに紐づくユーザーを取得します.
	 * @param id id
	 * @return ユーザー
	 */
	public UserEntity findOneById(Integer id);
	/**
	 * 指定した社員番号に紐づくユーザーを取得します.
	 * @param id id
	 * @return ユーザー
	 */
	public UserEntity findOneByEmployeeNumber(String employeeNumber);
	/**
	 * 未削除ユーザー一覧を取得.
	 * @param
	 * @return ユーザー一覧（未削除）
	 */
	public List<UserEntity> findByIsDeletedFalse();
}