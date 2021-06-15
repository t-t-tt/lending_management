package taro.service;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taro.entity.EquipEntity;
import taro.entity.LendEntity;
import taro.entity.UserEntity;
import taro.form.LendForm;
import taro.repository.EquipRepository;
import taro.repository.LendRepository;
import taro.repository.UserRepository;

/**
 * イベント参加者のService実装クラスです.
 */
@Service
public class LendServiceImpl implements LendService {
	@Autowired
	LendRepository lendRepository;
	@Autowired
	EquipRepository equipRepository;
	@Autowired
	UserRepository userRepository;

	/**
	 * 未削除貸出一覧を取得.
	 * @param
	 * @return 貸出一覧（未削除）
	 */
	public List<LendEntity> findByIsDeletedFalse() {
		return lendRepository.findByIsDeletedFalse();
	}

	/**
	 * 機器レコード内を検索()
	 * 貸出レコード内を検索、equipIdとuserIdが合致するレコードがあったら処理
	 * @param LendForm 貸出情報
	 */
	@Transactional
	public void rentPc(LendForm lendForm) {
		//機器コードを検索
		EquipEntity equip = equipRepository.findOneByAsset(lendForm.getAsset());
		//機器レコードがnull or 貸し出されていない
		if (equip == null || !equip.getIsLent())
			return;

		//機器レコードが存在
		UserEntity user = userRepository.findOneByEmployeeNumber(lendForm.getEmployeeNumber());

		//機器レコードがnull
		if (user == null)
			return;
		else {
			//機器レコード書き換え
			equip.setIsLent(true);
			equipRepository.save(equip);

			//貸出レコードに追加
			LendEntity lend = new LendEntity();
			lend.setEquipId(equip.getId());
			lend.setUserId(user.getId());
			lend.setLendStart(lendForm.getLendStart());
			lend.setLendEnd(lendForm.getLendEnd());
			lend.setRemarks(lendForm.getRemarks());
			lend.setRegistrationDate(new Date(System.currentTimeMillis()));
			lend.setUpdateDate(new Date(System.currentTimeMillis()));

			// LendEntityクラスの情報を使ってDBに登録する処理を実行
			lendRepository.save(lend);
		}
	}

	/**
	 * 貸出レコード内を検索、equipIdとuserIdが合致するレコードがあったら処理
	 * 機器レコードのisLentを書き換え
	 * 貸出レコードの該当情報を削除
	 * @param id 貸出ID
	 */
	@Transactional
	public void dropOffPc(LendForm lendForm) {
		//機器コードを検索
		EquipEntity equip = equipRepository.findOneByAsset(lendForm.getAsset());

		//機器レコードがnull or 貸し出されていない
		if (equip == null || equip.getIsLent())
			return;

		LendEntity lend = lendRepository.findOneByEquipId(equip.getId());

		UserEntity user = userRepository.findOneById(lend.getUserId());

		//機器レコードがnull
		if (user == null)
			return;
		else {
			equip.setIsLent(false);
			equipRepository.save(equip);
			lendRepository.deleteById(lend.getId());
		}

	}
}