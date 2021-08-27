package taro.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import taro.entity.UserEntity;

/**
 * UserServiceクラスのテストクラスです.
 */
@SpringBootTest
public class UserServiceTest {

	@Autowired
	UserService user_tableEntryService;

	// 単体テスト結果検証用
	@Autowired
	UserService userService;

	// テストデータの準備
	@Test
	@Sql(statements = {
			"DELETE FROM user_table",
			"ALTER TABLE user_table auto_increment = 1",
			"INSERT INTO user_table(age,department,employee_number,gender,is_deleted,mail,name,name_kana,position,privilege,registration_date,retirement_date,tel_number,update_date) VALUES (49, '開発1課', 'A0001', '女', 0, 'katsumi124@clpxtxlcu.ff','森脇克美', 'モリワキカツミ', '一般', '利用者', '2021-03-01',null, 235868783, '2021-03-01')",
			"INSERT INTO user_table(age,department,employee_number,gender,is_deleted,mail,name,name_kana,position,privilege,registration_date,retirement_date,tel_number,update_date) VALUES (55, '開発2課', 'A0002', '女', 0, 'amanabe@lkfolssvfl.hczpp.pc','真鍋沙奈', 'マナベサナ', '一般', '利用者', '2021-03-01',null, 264640388, '2021-03-01')",
			"INSERT INTO user_table(age,department,employee_number,gender,is_deleted,mail,name,name_kana,position,privilege,registration_date,retirement_date,tel_number,update_date) VALUES (25, '開発2課', 'A0003', '女', 0, 'Aki_Takanashi@ajtkcn.yk','高梨愛姫', 'タカナシアキ', '一般', '利用者', '2021-03-01',null, 733344955, '2021-03-01')",
			"INSERT INTO user_table(age,department,employee_number,gender,is_deleted,mail,name,name_kana,position,privilege,registration_date,retirement_date,tel_number,update_date) VALUES (46, '開発2課', 'A0004', '女', 0, 'ayaka53863@hnrwwzt.ouk','浅川彩香', 'アサカワアヤカ', '一般', '利用者', '2021-03-01',null, 733344944, '2021-03-01')",
			"INSERT INTO user_table(age,department,employee_number,gender,is_deleted,mail,name,name_kana,position,privilege,registration_date,retirement_date,tel_number,update_date) VALUES (22, '開発2課', 'A0005', '女', 0, 'rinkayamashiro@iriqg.bkxcb.ttc','山城凛香', 'ヤマシロリンカ', '一般', '利用者', '2021-03-01',null, 733344333, '2021-03-01')",
			"INSERT INTO user_table(age,department,employee_number,gender,is_deleted,mail,name,name_kana,position,privilege,registration_date,retirement_date,tel_number,update_date) VALUES (55, '開発1課', 'A0006', '女', 0, 'mizuki_ikuta@dkjnjxka.lran.sk','生田瑞紀', 'イクタミズキ', '一般', '利用者', '2021-03-01','2021/5/30', 733344222, '2021-03-01')",
			"INSERT INTO user_table(age,department,employee_number,gender,is_deleted,mail,name,name_kana,position,privilege,registration_date,retirement_date,tel_number,update_date) VALUES (26, '開発1課', 'A0007', '男', 0, 'gl-fgujhlzunawvmineto16563@qdywk.nwned.fk','小峰嶺渡', 'コミネミネト', '一般', '利用者', '2021-03-01',null, 733344111, '2021-03-01')",
			"INSERT INTO user_table(age,department,employee_number,gender,is_deleted,mail,name,name_kana,position,privilege,registration_date,retirement_date,tel_number,update_date) VALUES (50, '開発1課', 'A0008', '男', 0, 'shinichi117@htnsvbdwmx.df.hi','紺野信一', 'コンノシンイチ', '一般', '利用者', '2021-03-01',null, 733344819, '2021-03-01')",
			"INSERT INTO user_table(age,department,employee_number,gender,is_deleted,mail,name,name_kana,position,privilege,registration_date,retirement_date,tel_number,update_date) VALUES (53, '開発1課', 'A0009', '男', 0, 'Masayoshi_Hatano@mwxuaibo.nxxo.cc','波多野正義', 'ハタノマサヨシ', '一般', '利用者', '2021-03-01',null, 733344351, '2021-03-01')",
			"INSERT INTO user_table(age,department,employee_number,gender,is_deleted,mail,name,name_kana,position,privilege,registration_date,retirement_date,tel_number,update_date) VALUES (36, '開発1課', 'A0010', '女', 0, 'oyamaguchi@hfyc.jvj','山口桜子', 'ヤマグチサクラコ', '一般', '利用者', '2021-03-01',null, 733344968, '2021-03-01')",
			"INSERT INTO user_table(age,department,employee_number,gender,is_deleted,mail,name,name_kana,position,privilege,registration_date,retirement_date,tel_number,update_date) VALUES (40, '開発1課', 'A0011', '女', 0, 'Keiko_Ban@ydtq.vi.qot','伴景子', 'バンケイコ', '部長', '管理者', '2021-03-01',null, 733344930, '2021-03-01')",


	})
	public void generateUserTestData() {
		UserEntity user = userService.findOneById(1);
		assertEquals(user.getAge(),49);
	}
}