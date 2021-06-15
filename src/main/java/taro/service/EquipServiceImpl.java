package taro.service;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taro.entity.EquipEntity;
import taro.form.EquipForm;
import taro.repository.EquipRepository;

/**
 * イベント参加者のService実装クラスです.
 */
@Service
public class EquipServiceImpl implements EquipService {
	@Autowired
	EquipRepository equipRepository;

	/**
	 * 指定したIDに紐づくユーザーを取得します.
	 * @param id id
	 * @return ユーザー
	 */
	public EquipEntity findOneById(Integer id) {
		return equipRepository.findOneById(id);
	}

	/**
	 * 未削除ユーザー一覧を取得.
	 * @param
	 * @return ユーザー一覧（未削除）
	 */
	public List<EquipEntity> findByIsDeletedFalse() {
		return equipRepository.findByIsDeletedFalse();
	}

	/**
	 * ユーザー情報をDBに登録します.
	 * @param EquipForm ユーザー情報
	 */
	public void save(EquipForm equipForm) {
		// EquipForm内の情報をEquipEntityクラスに詰め替え
		EquipEntity equip = new EquipEntity();
		equip.setAsset(equipForm.getAsset());
		equip.setMaker(equipForm.getMaker());
		equip.setOs(equipForm.getOs());
		equip.setMemory(equipForm.getMemory());
		equip.setCapacity(equipForm.getCapacity());
		equip.setHasGraphicBoard(equipForm.getHasGraphicBoard());
		equip.setIsBroken(equipForm.getIsBroken());
		equip.setIsDeleted(false);
		equip.setIsLent(false);
		equip.setStorageLocation(equipForm.getStorageLocation());
		equip.setLeaseStart(Date.valueOf(equipForm.getLeaseStart()));
		equip.setLeaseEnd(Date.valueOf(equipForm.getLeaseEnd()));
		if (equipForm.getInventoryDate() != null && equipForm.getInventoryDate() != "")
			equip.setInventoryDate(Date.valueOf(equipForm.getInventoryDate()));
		equip.setRemarks(equipForm.getRemarks());
		equip.setRegistrationDate(new Date(System.currentTimeMillis()));
		equip.setUpdateDate(new Date(System.currentTimeMillis()));

		// EquipEntityクラスの情報を使ってDBに登録する処理を実行
		equipRepository.save(equip);
	}

	/**
	 * ユーザー情報をDBに登録します.
	 * @param EquipForm ユーザー情報
	 * @param id ユーザーID
	 */
	@Transactional
	public void save(Integer id, EquipForm equipForm) {
		// EquipForm内の情報をEquipEntityクラスに詰め替え
		EquipEntity equip = equipRepository.findOneById(id);
		equip.setAsset(equipForm.getAsset());
		equip.setMaker(equipForm.getMaker());
		equip.setOs(equipForm.getOs());
		equip.setMemory(equipForm.getMemory());
		equip.setCapacity(equipForm.getCapacity());
		equip.setHasGraphicBoard(equipForm.getHasGraphicBoard());
		equip.setIsBroken(equipForm.getIsBroken());
		equip.setStorageLocation(equipForm.getStorageLocation());
		equip.setLeaseStart(Date.valueOf(equipForm.getLeaseStart()));
		equip.setLeaseEnd(Date.valueOf(equipForm.getLeaseEnd()));
		if (equipForm.getInventoryDate() != null && equipForm.getInventoryDate() != "")
			equip.setInventoryDate(Date.valueOf(equipForm.getInventoryDate()));
		equip.setRemarks(equipForm.getRemarks());
		equip.setUpdateDate(new Date(System.currentTimeMillis()));

		// EquipEntityクラスの情報を使ってDBに登録する処理を実行
		equipRepository.save(equip);
	}

	/**
	 * 指定したIDに紐づくレコードのis_deletedをfalseに書き換え.
	 * @param id ユーザーID
	 */
	@Transactional
	public void deleteById(Integer id) {
		EquipEntity equip = equipRepository.findOneById(id);
		equip.setIsDeleted(true);
		equipRepository.save(equip);
	}
}