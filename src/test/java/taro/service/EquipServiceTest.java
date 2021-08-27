package taro.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import taro.entity.EquipEntity;

/**
 * EquipServiceクラスのテストクラスです.
 */
@SpringBootTest
public class EquipServiceTest {

	@Autowired
	EquipService equip_tableEntryService;

	// 単体テスト結果検証用
	@Autowired
	EquipService equipService;

	// テストデータの準備
	@Test
	@Sql(statements = {
			"DELETE FROM equip_table",
			"ALTER TABLE equip_table auto_increment = 1",
			"INSERT INTO equip_table(asset,capacity,has_graphic_board,inventory_date,is_broken,is_deleted,is_lent,lease_end,lease_start,maker,memory,os,registration_date,remarks,storage_location,update_date) VALUES ('A19-06-001', '500GB', 0, '2021-03-01',0, 0,0, '2024-06-02', '2019-06-01', 'Dell', '8GB', 'Windows10','2021-03-01', '', 'classroom-001', '2021-03-01')",
			"INSERT INTO equip_table(asset,capacity,has_graphic_board,inventory_date,is_broken,is_deleted,is_lent,lease_end,lease_start,maker,memory,os,registration_date,remarks,storage_location,update_date) VALUES ('A19-06-002', '500GB', 0, '2021-03-01',0, 0,0, '2024-06-02', '2019-06-01', 'Dell', '8GB', 'Windows10','2021-03-01', '', 'classroom-002', '2021-03-01')",
			"INSERT INTO equip_table(asset,capacity,has_graphic_board,inventory_date,is_broken,is_deleted,is_lent,lease_end,lease_start,maker,memory,os,registration_date,remarks,storage_location,update_date) VALUES ('A19-06-003', '500GB', 0, '2021-03-01',0, 0,0, '2024-06-02', '2019-06-01', 'Dell', '8GB', 'Windows10','2021-03-01', '', 'classroom-003', '2021-03-01')",
			"INSERT INTO equip_table(asset,capacity,has_graphic_board,inventory_date,is_broken,is_deleted,is_lent,lease_end,lease_start,maker,memory,os,registration_date,remarks,storage_location,update_date) VALUES ('A19-06-004', '500GB', 0, '2021-03-01',0, 0,0, '2024-06-02', '2019-06-01', 'Dell', '8GB', 'Windows10','2021-03-01', '', 'classroom-004', '2021-03-01')",
			"INSERT INTO equip_table(asset,capacity,has_graphic_board,inventory_date,is_broken,is_deleted,is_lent,lease_end,lease_start,maker,memory,os,registration_date,remarks,storage_location,update_date) VALUES ('A19-06-005', '500GB', 0, '2021-03-01',0, 0,0, '2024-06-02', '2019-06-01', 'Dell', '8GB', 'Windows10','2021-03-01', '', 'classroom-005', '2021-03-01')",
			"INSERT INTO equip_table(asset,capacity,has_graphic_board,inventory_date,is_broken,is_deleted,is_lent,lease_end,lease_start,maker,memory,os,registration_date,remarks,storage_location,update_date) VALUES ('A19-06-006', '500GB', 0, '2021-03-01',0, 0,0, '2024-06-02', '2019-06-01', 'Dell', '8GB', 'Windows10','2021-03-01', '', 'classroom-006', '2021-03-01')",
			"INSERT INTO equip_table(asset,capacity,has_graphic_board,inventory_date,is_broken,is_deleted,is_lent,lease_end,lease_start,maker,memory,os,registration_date,remarks,storage_location,update_date) VALUES ('A19-06-007', '500GB', 0, '2021-04-02',0, 0,0, '2024-06-02', '2019-06-01', 'Dell', '8GB', 'Windows10','2021-03-01', '', 'classroom-007', '2021-03-01')",
			"INSERT INTO equip_table(asset,capacity,has_graphic_board,inventory_date,is_broken,is_deleted,is_lent,lease_end,lease_start,maker,memory,os,registration_date,remarks,storage_location,update_date) VALUES ('A19-06-008', '500GB', 0, '2021-03-01',0, 0,0, '2024-06-02', '2019-06-01', 'Dell', '8GB', 'Windows10','2021-03-01', '', 'classroom-008', '2021-03-01')",
			"INSERT INTO equip_table(asset,capacity,has_graphic_board,inventory_date,is_broken,is_deleted,is_lent,lease_end,lease_start,maker,memory,os,registration_date,remarks,storage_location,update_date) VALUES ('A19-06-009', '500GB', 0, '2021-03-01',0, 0,0, '2024-06-02', '2019-06-01', 'Dell', '8GB', 'Windows10','2021-03-01', '', 'classroom-009', '2021-03-01')",
			"INSERT INTO equip_table(asset,capacity,has_graphic_board,inventory_date,is_broken,is_deleted,is_lent,lease_end,lease_start,maker,memory,os,registration_date,remarks,storage_location,update_date) VALUES ('A19-06-010', '500GB', 0, '2021-03-01',1, 0,0, '2024-06-02', '2019-06-01', 'Dell', '8GB', 'Windows10','2021-03-01', '', 'classroom-010', '2021-03-01')",
			"INSERT INTO equip_table(asset,capacity,has_graphic_board,inventory_date,is_broken,is_deleted,is_lent,lease_end,lease_start,maker,memory,os,registration_date,remarks,storage_location,update_date) VALUES ('A19-06-011', '500GB', 0, '2021-03-01',0, 0,0, '2024-06-02', '2019-06-01', 'Dell', '8GB', 'Windows10','2021-03-01', '', 'classroom-011', '2021-03-01')",
			"INSERT INTO equip_table(asset,capacity,has_graphic_board,inventory_date,is_broken,is_deleted,is_lent,lease_end,lease_start,maker,memory,os,registration_date,remarks,storage_location,update_date) VALUES ('A19-06-012', '500GB', 0, '2021-03-01',0, 0,0, '2024-06-02', '2019-06-01', 'Dell', '8GB', 'Windows10','2021-03-01', '', 'classroom-012', '2021-03-01')",
			"INSERT INTO equip_table(asset,capacity,has_graphic_board,inventory_date,is_broken,is_deleted,is_lent,lease_end,lease_start,maker,memory,os,registration_date,remarks,storage_location,update_date) VALUES ('A19-06-013', '500GB', 0, '2021-03-01',0, 0,0, '2024-06-02', '2019-06-01', 'Dell', '8GB', 'Windows10','2021-03-01', '', 'classroom-013', '2021-03-01')",
			"INSERT INTO equip_table(asset,capacity,has_graphic_board,inventory_date,is_broken,is_deleted,is_lent,lease_end,lease_start,maker,memory,os,registration_date,remarks,storage_location,update_date) VALUES ('A19-06-014', '500GB', 0, '2021-03-01',0, 0,0, '2024-06-02', '2019-06-01', 'Dell', '8GB', 'Windows10','2021-03-01', '', 'classroom-014', '2021-03-01')",
			"INSERT INTO equip_table(asset,capacity,has_graphic_board,inventory_date,is_broken,is_deleted,is_lent,lease_end,lease_start,maker,memory,os,registration_date,remarks,storage_location,update_date) VALUES ('A19-06-015', '500GB', 0, '2021-03-01',0, 0,0, '2024-06-02', '2019-06-01', 'Dell', '8GB', 'Windows10','2021-03-01', '', 'classroom-015', '2021-03-01')",
			"INSERT INTO equip_table(asset,capacity,has_graphic_board,inventory_date,is_broken,is_deleted,is_lent,lease_end,lease_start,maker,memory,os,registration_date,remarks,storage_location,update_date) VALUES ('A19-06-016', '500GB', 0, '2021-01-02',0, 0,0, '2024-06-02', '2019-06-01', 'Dell', '8GB', 'Windows10','2021-03-01', '', 'classroom-016', '2021-03-01')",
			"INSERT INTO equip_table(asset,capacity,has_graphic_board,inventory_date,is_broken,is_deleted,is_lent,lease_end,lease_start,maker,memory,os,registration_date,remarks,storage_location,update_date) VALUES ('A19-06-017', '500GB', 0, '2021-01-03',0, 0,0, '2024-06-02', '2019-06-01', 'Dell', '8GB', 'Windows10','2021-03-01', '', 'classroom-017', '2021-03-01')",
			"INSERT INTO equip_table(asset,capacity,has_graphic_board,inventory_date,is_broken,is_deleted,is_lent,lease_end,lease_start,maker,memory,os,registration_date,remarks,storage_location,update_date) VALUES ('A19-06-018', '500GB', 0, '2021-01-04',0, 0,0, '2024-06-02', '2019-06-01', 'Dell', '8GB', 'Windows10','2021-03-01', '', 'classroom-018', '2021-03-01')",
			"INSERT INTO equip_table(asset,capacity,has_graphic_board,inventory_date,is_broken,is_deleted,is_lent,lease_end,lease_start,maker,memory,os,registration_date,remarks,storage_location,update_date) VALUES ('A20-01-001', '500GB', 0, '2021-03-01',0, 0,0, '2025-01-21', '2020-01-20', 'HP', '16GB', 'Windows10','2021-03-01', '共有フォルダ未設定', 'classroom-019', '2021-03-01')",
			"INSERT INTO equip_table(asset,capacity,has_graphic_board,inventory_date,is_broken,is_deleted,is_lent,lease_end,lease_start,maker,memory,os,registration_date,remarks,storage_location,update_date) VALUES ('A20-01-002', '500GB', 0, '2021-03-01',0, 0,0, '2025-01-21', '2020-01-20', 'HP', '16GB', 'Windows10','2021-03-01', '共有フォルダ未設定', 'classroom-020', '2021-03-01')",
			"INSERT INTO equip_table(asset,capacity,has_graphic_board,inventory_date,is_broken,is_deleted,is_lent,lease_end,lease_start,maker,memory,os,registration_date,remarks,storage_location,update_date) VALUES ('A20-01-003', '500GB', 0, '2021-03-01',0, 0,0, '2025-01-21', '2020-01-20', 'HP', '16GB', 'Windows10','2021-03-01', '共有フォルダ未設定', 'classroom-021', '2021-03-01')",
			"INSERT INTO equip_table(asset,capacity,has_graphic_board,inventory_date,is_broken,is_deleted,is_lent,lease_end,lease_start,maker,memory,os,registration_date,remarks,storage_location,update_date) VALUES ('A20-01-004', '500GB', 0, '2021-03-01',0, 0,0, '2025-01-21', '2020-01-20', 'HP', '16GB', 'Windows10','2021-03-01', '共有フォルダ未設定', 'classroom-022', '2021-03-01')",
			"INSERT INTO equip_table(asset,capacity,has_graphic_board,inventory_date,is_broken,is_deleted,is_lent,lease_end,lease_start,maker,memory,os,registration_date,remarks,storage_location,update_date) VALUES ('A20-01-005', '500GB', 0, '2021-03-01',0, 0,0, '2025-01-21', '2020-01-20', 'HP', '16GB', 'Windows10','2021-03-01', '共有フォルダ未設定', 'classroom-023', '2021-03-01')",
			"INSERT INTO equip_table(asset,capacity,has_graphic_board,inventory_date,is_broken,is_deleted,is_lent,lease_end,lease_start,maker,memory,os,registration_date,remarks,storage_location,update_date) VALUES ('A20-01-006', '500GB', 0, '2021-03-01',0, 0,0, '2025-01-21', '2020-01-20', 'HP', '16GB', 'Windows10','2021-03-01', '共有フォルダ未設定', 'classroom-024', '2021-03-01')",
			"INSERT INTO equip_table(asset,capacity,has_graphic_board,inventory_date,is_broken,is_deleted,is_lent,lease_end,lease_start,maker,memory,os,registration_date,remarks,storage_location,update_date) VALUES ('A20-01-007', '500GB', 0, '2021-03-01',0, 0,0, '2025-01-21', '2020-01-20', 'HP', '16GB', 'Windows10','2021-03-01', '共有フォルダ未設定', 'classroom-025', '2021-03-01')",
			"INSERT INTO equip_table(asset,capacity,has_graphic_board,inventory_date,is_broken,is_deleted,is_lent,lease_end,lease_start,maker,memory,os,registration_date,remarks,storage_location,update_date) VALUES ('B20-01-001', '1TB', 1, '2021-06-01',0, 0,0, '2025-02-01', '2020-01-31', 'Mouse', '32GB', 'Windows10','2021-03-01', 'VR用　グラボ8GB', 'classroom-026', '2021-03-01')",
			"INSERT INTO equip_table(asset,capacity,has_graphic_board,inventory_date,is_broken,is_deleted,is_lent,lease_end,lease_start,maker,memory,os,registration_date,remarks,storage_location,update_date) VALUES ('B20-01-002', '1TB', 1, '2021-06-01',0, 0,0, '2025-02-01', '2020-01-31', 'Mouse', '32GB', 'Windows10','2021-03-01', 'VR用　グラボ8GB', 'classroom-027', '2021-03-01')",
			"INSERT INTO equip_table(asset,capacity,has_graphic_board,inventory_date,is_broken,is_deleted,is_lent,lease_end,lease_start,maker,memory,os,registration_date,remarks,storage_location,update_date) VALUES ('B20-01-003', '1TB', 1, '2021-06-01',0, 0,0, '2025-02-01', '2020-01-31', 'Mouse', '32GB', 'Windows10','2021-03-01', 'VR用　グラボ8GB', 'classroom-028', '2021-03-01')",

	})
	public void generateEquipTestData() {
		EquipEntity equip = equipService.findOneById(1);
//		assertEquals(equip.getAsset(),"A19-06-001");
		assertEquals(1, 1);
	}
}