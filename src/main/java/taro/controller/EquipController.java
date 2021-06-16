package taro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import taro.entity.EquipEntity;
import taro.entity.LendEntity;
import taro.entity.UserEntity;
import taro.form.EquipForm;
import taro.service.EquipService;
import taro.service.LendService;
import taro.service.UserService;

/**
 * 機器コントローラクラスです.
 */
@Controller
public class EquipController {

	@Autowired
	EquipService equipService;

	@Autowired
	LendService lendService;

	@Autowired
	UserService userService;

	@GetMapping("/equip")
	public String index(Model model) {
		// 次に表示する画面のパスを返却
		return "redirect:/equip/list";
	}

	/**
	 * 機器一覧を表示します.
	 * @param model
	 * @return 機器一覧画面のパス
	 */
	@GetMapping("/equip/list")
	public String equipList(Model model) {
		// DBに登録されている機器の一覧を取得
		List<EquipEntity> equipList = equipService.findByIsDeletedFalse();
		System.out.println(equipList.size() + "機器一覧");

		int letedPcNum = 0;
		for (EquipEntity equip : equipList) {
			if (equip.getIsLent())
				letedPcNum++;
		}

		// modelにイベントの一覧をセット
		model.addAttribute("equipList", equipList);
		model.addAttribute("lentedPcNum", letedPcNum);
		model.addAttribute("notLentedPcNum", (equipList.size() - letedPcNum));

		// 次に表示する画面のパスを返却
		return "equip/list";
	}

	/**
	 * 機器詳細を表示します.
	 * @param model
	 * @return 機器一覧画面のパス
	 * @throws Exception
	 */
	@GetMapping("/equip/{id}")
	public String equipDetail(@PathVariable("id") Integer id,Model model) throws Exception {
		// DBに登録されている機器の一覧を取得
		EquipEntity equip = equipService.findOneById(id);

		if(equip == null) throw new Exception("機器データが取得できませんでした。");

		if(equip.getIsLent()) {
			LendEntity lend = lendService.findOneByEquipId(id);
			if(lend == null) throw new Exception("貸出データが取得できませんでした。");
			else {
				UserEntity user = userService.findOneById(lend.getUserId());
				if(user == null) throw new Exception("ユーザーデータが取得できませんでした。");
				model.addAttribute("user", user);
			}
		}

		System.out.println(equip);

		// modelにイベントの一覧をセット
		model.addAttribute("equip", equip);

		// 次に表示する画面のパスを返却
		return "equip/detail";
	}

	/**
	 * 機器登録画面を表示します.
	 * @param model
	 * @return 機器一覧画面のパス
	 */
	@GetMapping("/equip/regist")
	public String equipRegist() {
		// 次に表示する画面のパスを返却
		return "/equip/regist";
	}

	/**
	 * 機器情報の登録処理.
	 * @param model
	 * @return 機器一覧画面のパス
	 */
	@PostMapping("/equip/add")
	public String equipAdd(@Validated @ModelAttribute("equip") EquipForm equipForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			System.out.println(equipForm);
			return "redirect:/equip/regist";
		}
		System.out.println(equipForm);
		// DBに登録
		equipService.save(equipForm);

		// 次に表示する画面のパスを返却
		return "redirect:/equip/list";
	}

	/**
	 * 機器情報の更新処理
	 * @param model
	 * @return 機器一覧画面のパス
	 */
	@PostMapping("/equip/{id}/update")
	public String equipUpdate(@PathVariable("id") Integer id, @Validated @ModelAttribute("equip") EquipForm equipForm,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			System.out.println(equipForm);
			return "error.html";
		}
		// DBに登録
		equipService.save(id, equipForm);

		// 次に表示する画面のパス（htmlファイルの名称）を返却
		return "redirect:/equip/list";
	}

	/**
	 * 機器情報の削除処理
	 * @param model
	 * @return 機器一覧画面のパス
	 * @throws Exception
	 */
	@GetMapping("/equip/{id}/delete")
	public String equipDelete(@PathVariable("id") Integer id) throws Exception {
		if (lendService.findOneByEquipId(id) != null)
			throw new Exception("現在貸出中の機器です。");
		equipService.deleteById(id);
		// 次に表示する画面のパス（htmlファイルの名称）を返却
		return "redirect:/equip/list";
	}
}