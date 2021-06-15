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
import taro.form.EquipForm;
import taro.service.EquipService;

/**
 * 機器コントローラクラスです.
 */
@Controller
public class EquipController {

	@Autowired
	EquipService equipService;

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
		System.out.println(equipList.size()+"機器一覧");

		int letedPcNum = 0;
		for (EquipEntity equip:equipList) {
			if(equip.getIsLent()) letedPcNum++;
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
	 */
	@GetMapping("/equip/{id}")
	public String equipDetail(@PathVariable("id") Integer id,Model model) {
		// DBに登録されている機器の一覧を取得
		EquipEntity equip = equipService.findOneById(id);
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
		if(bindingResult.hasErrors()) {
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
	public String equipUpdate(@PathVariable("id") Integer id,@Validated @ModelAttribute("equip") EquipForm equipForm, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
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
	 */
	@GetMapping("/equip/{id}/delete")
	public String equipDelete(@PathVariable("id") Integer id) {
		equipService.deleteById(id);
		// 次に表示する画面のパス（htmlファイルの名称）を返却
		return "redirect:/equip/list";
	}
}