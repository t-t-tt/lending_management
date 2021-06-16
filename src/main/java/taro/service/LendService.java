package taro.service;

import java.util.List;

import taro.entity.LendEntity;
import taro.entity.LendingManagement;
import taro.form.LendForm;

/**
 * イベント参加者のServiceインターフェースです.
 */
public interface LendService {
	/**
	 * 貸出ID
	 * @param id 貸出ID
	 */
	public LendEntity findOneById(Integer id);

	/**
	 * 指定したIDに紐づく貸出を取得します.
	 * @param userId ユーザーID
	 * @return 貸出リスト
	 */
	public List<LendEntity> findByUserId(Integer usesrId);

	/**
	 * 指定したIDに紐づく貸出を取得します.
	 * @param equipId 機器Id
	 * @return 貸出
	 */
	public LendEntity findOneByEquipId(Integer equipId);

	/**
	 * 未削除貸出一覧を取得.
	 * @param
	 * @return 貸出一覧（未削除）
	 */
	public List<LendingManagement> findAll();

	/**
	 * 貸出情報を更新します.
	 * @param LendForm 貸出情報
	 * @throws Exception
	 */
	public void editLend(LendForm lendForm) throws Exception;

	/**
	 * 貸出情報をDBに登録します.
	 * @param LendForm 貸出情報
	 * @throws Exception
	 */
	public void rentPc(LendForm lendForm) throws Exception;

	/**
	 * 指定したIDに紐づくレコード削除.
	 * @param id 貸出ID
	 * @throws Exception
	 */
	public void dropOffPc(LendForm lendForm) throws Exception;
}