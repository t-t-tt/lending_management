package taro.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taro.entity.EquipEntity;
import taro.entity.LendEntity;
import taro.entity.LendingManagement;
import taro.entity.UserEntity;
import taro.form.LendForm;
import taro.repository.EquipRepository;
import taro.repository.LendRepository;
import taro.repository.UserRepository;

/**
 * 貸出のService実装クラスです.
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
	 * 貸出ID
	 * @param id 貸出ID
	 */
	public LendEntity findOneById(Integer id) {
		return lendRepository.findOneById(id);
	}

	/**
	 * ユーザーID
	 * @param userId ユーザーID
	 */
	public List<LendEntity> findByUserId(Integer userId) {
		return lendRepository.findByUserId(userId);
	}

	/**
	 * 機器ID
	 * @param userId 機器ID
	 */
	public LendEntity findOneByEquipId(Integer equipId) {
		return lendRepository.findOneByEquipId(equipId);
	}

	/**
	 * 未削除貸出一覧を取得.
	 * @param
	 * @return 貸出一覧（未削除）
	 */
	@Transactional
	public List<LendingManagement> findAll() {
		List<EquipEntity> equipList = equipRepository.findByIsDeletedFalse();
		List<LendingManagement> lendManagementList = new ArrayList<LendingManagement>();
		Integer count = 1;
		for (EquipEntity equip : equipList) {
			LendingManagement lendingManagement = new LendingManagement();
			lendingManagement.setEquip(equip);
			lendingManagement.setCount(count++);
			if (equip.getIsLent()) {
				LendEntity lend = lendRepository.findOneByEquipId(equip.getId());
				UserEntity user = userRepository.findOneById(lend.getUserId());
				lendingManagement.setUser(user);
				lendingManagement.setLend(lend);
				;
			}
			lendManagementList.add(lendingManagement);
		}
		return lendManagementList;
	}

	/**
	 * 機器レコード内を検索()
	 * 貸出レコード内を検索、equipIdとuserIdが合致するレコードがあったら処理
	 * @param LendForm 貸出情報
	 * @throws Exception
	 */
	@Transactional
	public void editLend(LendForm lendForm) throws Exception {
		//機器コードを検索
		EquipEntity equip = equipRepository.findOneById(lendForm.getEquipId());
		System.out.println(equip);
		//機器レコードがnull or 貸出中
		if (equip == null)
			throw new Exception("機器データが存在しません");
		else if (!equip.getIsLent())
			throw new Exception("貸出されていない機器です。");
		else {
			//機器レコードが存在
			UserEntity user = userRepository.findOneById(lendForm.getUserId());
			System.out.println(user);

			//ユーザーレコードがnull
			if (user == null)
				throw new Exception("ユーザーデータが存在しません。");
			else {
				//貸出レコードに追加
				LendEntity lend = lendRepository.findOneByEquipId(lendForm.getEquipId());
				lend.setLendStart(Date.valueOf(lendForm.getLendStart()));
				lend.setLendEnd(Date.valueOf(lendForm.getLendEnd()));
				lend.setRemarks(lendForm.getRemarks());
				lend.setUpdateDate(new Date(System.currentTimeMillis()));

				// LendEntityクラスの情報を使ってDBに登録する処理を実行
				lendRepository.save(lend);
			}
		}
	}

	/**
	 * 機器レコード内を検索()
	 * 貸出レコード内を検索、equipIdとuserIdが合致するレコードがあったら処理
	 * @param LendForm 貸出情報
	 * @throws Exception
	 */
	@Transactional
	public void rentPc(LendForm lendForm) throws Exception {
		//機器コードを検索
		EquipEntity equip = equipRepository.findOneById(lendForm.getEquipId());
		System.out.println(equip);
		//機器レコードがnull or 貸出中
		if (equip == null)
			throw new Exception("機器データが存在しません");
		else if (equip.getIsLent())
			throw new Exception("現在貸出中です");
		else {
			//機器レコードが存在
			UserEntity user = userRepository.findOneById(lendForm.getUserId());
			System.out.println(user);

			//ユーザーレコードがnull
			if (user == null)
				throw new Exception("ユーザーデータが存在しません。");
			else {
				//機器レコード書き換え
				equip.setIsLent(true);
				equipRepository.save(equip);

				//貸出レコードに追加
				LendEntity lend = new LendEntity();
				lend.setEquipId(lendForm.getEquipId());
				lend.setUserId(lendForm.getUserId());
				lend.setLendStart(Date.valueOf(lendForm.getLendStart()));
				lend.setLendEnd(Date.valueOf(lendForm.getLendEnd()));
				lend.setRemarks(lendForm.getRemarks());
				lend.setRegistrationDate(new Date(System.currentTimeMillis()));
				lend.setUpdateDate(new Date(System.currentTimeMillis()));

				// LendEntityクラスの情報を使ってDBに登録する処理を実行
				lendRepository.save(lend);
			}
		}
	}

	/**
	 * 貸出レコード内を検索、equipIdとuserIdが合致するレコードがあったら処理
	 * 機器レコードのisLentを書き換え
	 * 貸出レコードの該当情報を削除
	 * @param id 貸出ID
	 * @throws Exception
	 */
	@Transactional
	public void dropOffPc(LendForm lendForm) throws Exception {
		//機器コードを検索
		EquipEntity equip = equipRepository.findOneById(lendForm.getEquipId());

		//機器レコードがnull or 貸し出されていない
		if (equip == null)
			throw new Exception("機器レコードが存在しません");
		else if (!equip.getIsLent())
			throw new Exception("現在貸出されておりません");
		else {
			UserEntity user = userRepository.findOneById(lendForm.getUserId());
			//機器レコードがnull
			if (user == null)
				throw new Exception("ユーザーデータが存在しません。");
			else {
				LendEntity lend = lendRepository.findOneByEquipId(equip.getId());
				equip.setIsLent(false);
				equipRepository.save(equip);
				lendRepository.deleteById(lend.getId());
			}
		}

	}
}