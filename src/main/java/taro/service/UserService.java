package taro.service;

import java.util.List;

import taro.entity.UserEntity;
import taro.form.UserForm;

/**
 * イベント参加者のServiceインターフェースです.
 */
public interface UserService {
	/**
	 * 指定したIDに紐づくユーザーを取得します.
	 * @param id id
	 * @return ユーザー
	 */
	public UserEntity findOneById(Integer id);

	/**
	 * 未削除ユーザー一覧を取得.
	 * @param
	 * @return ユーザー一覧
	 */
	public List<UserEntity> findByIsDeletedFalse();

	/**
	 * 未削除かつ未退職ユーザー一覧を取得.
	 * @param
	 * @return ユーザー一覧
	 */
	public List<UserEntity> findByIsDeletedFalseAndRetirementDateIsNull();

	/**
	 * 未削除かつ未退職ユーザー一覧を取得.
	 * @param
	 * @return ユーザー一覧
	 */
	public List<UserEntity> findByNameContainingAndIsDeletedFalse(String name);

	/**
	 * ユーザー情報をDBに登録します.
	 * @param UserForm ユーザー情報
	 */
	public void save(UserForm userForm);
	/**
	 * ユーザー情報をDBに登録します.
	 * @param UserForm ユーザー情報
	 * @param id ユーザーID
	 */
	public void save(Integer id, UserForm userForm) throws Exception;
	/**
	 * 指定したIDに紐づくレコード削除.
	 * @param id ユーザーID
	 */
	public void deleteById(Integer id);
}