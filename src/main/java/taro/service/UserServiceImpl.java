package taro.service;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taro.entity.UserEntity;
import taro.form.UserForm;
import taro.repository.UserRepository;

/**
 * イベント参加者のService実装クラスです.
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

	/**
	 * 指定したIDに紐づくユーザーを取得します.
	 * @param id id
	 * @return ユーザー
	 */
	public UserEntity findOneById(Integer id) {
		return userRepository.findOneById(id);
	}

	/**
	 * 未削除ユーザー一覧を取得.
	 * @param
	 * @return ユーザー一覧（未削除）
	 */
	public List<UserEntity> findByIsDeletedFalse() {
		return userRepository.findByIsDeletedFalse();
	}

	/**
	 * ユーザー情報をDBに登録します.
	 * @param UserForm ユーザー情報
	 */
	public void save(UserForm userForm) {
		// UserForm内の情報をUserEntityクラスに詰め替え
		UserEntity user = new UserEntity();
		user.setAge(userForm.getAge());
		user.setDepartment(userForm.getDepartment());
		user.setEmployeeNumber(userForm.getEmployeeNumber());
		user.setGender(userForm.getGender());
		user.setIsDeleted(false);
		user.setMail(userForm.getMail());
		user.setName(userForm.getName());
		user.setNameKana(userForm.getNameKana());
		user.setPosition(userForm.getPosition());
		user.setPrivilege(userForm.getPrivilege());
		user.setRegistrationDate(new Date(System.currentTimeMillis()));
		if (userForm.getRetirementDate() != null && userForm.getRetirementDate() != "")
			user.setRetirementDate(Date.valueOf(userForm.getRetirementDate()));
		user.setTelNumber(userForm.getTelNumber());
		user.setUpdateDate(new Date(System.currentTimeMillis()));

		// UserEntityクラスの情報を使ってDBに登録する処理を実行
		userRepository.save(user);
	}

	/**
	 * ユーザー情報をDBに登録します.
	 * @param UserForm ユーザー情報
	 * @param id ユーザーID
	 */
	@Transactional
	public void save(Integer id, UserForm userForm) {
		// UserForm内の情報をUserEntityクラスに詰め替え
		UserEntity user = userRepository.findOneById(id);
		System.out.println(user);
		user.setAge(userForm.getAge());
		user.setDepartment(userForm.getDepartment());
		user.setEmployeeNumber(userForm.getEmployeeNumber());
		user.setGender(userForm.getGender());
		user.setIsDeleted(false);
		user.setMail(userForm.getMail());
		user.setName(userForm.getName());
		user.setNameKana(userForm.getNameKana());
		user.setPosition(userForm.getPosition());
		user.setPrivilege(userForm.getPrivilege());
		if (userForm.getRetirementDate() != null && userForm.getRetirementDate() != "")
			user.setRetirementDate(Date.valueOf(userForm.getRetirementDate()));
		user.setTelNumber(userForm.getTelNumber());
		user.setUpdateDate(new Date(System.currentTimeMillis()));

		// UserEntityクラスの情報を使ってDBに登録する処理を実行
		userRepository.save(user);
	}

	/**
	 * 指定したIDに紐づくレコードのis_deletedをfalseに書き換え.
	 * @param id ユーザーID
	 */
	@Transactional
	public void deleteById(Integer id) {
		UserEntity user = userRepository.findOneById(id);
		user.setIsDeleted(true);
		userRepository.save(user);
	}
}