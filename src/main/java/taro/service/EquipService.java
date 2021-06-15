package taro.service;

import java.util.List;

import taro.entity.EquipEntity;
import taro.form.EquipForm;

/**
 * イベント参加者のServiceインターフェースです.
 */
public interface EquipService {
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
	 * 機器情報をDBに登録します.
	 * @param EquipForm 機器情報
	 */
	public void save(EquipForm equipForm);
	/**
	 * 機器情報をDBに登録します.
	 * @param EquipForm 機器情報
	 * @param id 機器ID
	 */
	public void save(Integer id, EquipForm equipForm);
	/**
	 * 指定したIDに紐づくレコード削除.
	 * @param id 機器ID
	 */
	public void deleteById(Integer id);
}