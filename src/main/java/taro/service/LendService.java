package taro.service;

import java.util.List;

import taro.entity.LendingManagement;
import taro.form.LendForm;

/**
 * イベント参加者のServiceインターフェースです.
 */
public interface LendService {
	/**
	 * 指定したIDに紐づく貸出を取得します.
	 * @param id id
	 * @return 貸出
	 */
//	public LendEntity findOneById(Integer id);

	/**
	 * 未削除貸出一覧を取得.
	 * @param
	 * @return 貸出一覧（未削除）
	 */
	public List<LendingManagement> findAll();

	/**
	 * 貸出情報をDBに登録します.
	 * @param LendForm 貸出情報
	 */
	public void rentPc(LendForm lendForm);
	/**
	 * 指定したIDに紐づくレコード削除.
	 * @param id 貸出ID
	 */
	public void dropOffPc(LendForm lendForm);
}