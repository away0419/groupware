package com.it.groupware.electronic.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.io.IOUtils;
import org.apache.poi.poifs.property.Child;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.it.groupware.boardFile.model.BoardFileDTO;
import com.it.groupware.common.ConstUtil;
import com.it.groupware.common.CustomErrorCode;
import com.it.groupware.common.CustomException;
import com.it.groupware.common.DataNameTransform;
import com.it.groupware.common.FileUploadUtil;
import com.it.groupware.common.ImageTranseform;
import com.it.groupware.common.PaginationInfo;
import com.it.groupware.common.SearchVO;
import com.it.groupware.department.model.DepartmentDTO;
import com.it.groupware.department.model.DepartmentService;
import com.it.groupware.electronic.model.ElectronicDTO;
import com.it.groupware.electronic.model.ElectronicRecentlyDTO;
import com.it.groupware.electronic.model.ElectronicService;
import com.it.groupware.electronicAppLine.model.ElectronicAppLineDTO;
import com.it.groupware.electronicAppLine.model.ElectronicAppLineService;
import com.it.groupware.electronicAppStamp.model.ElectronicAppStampDTO;
import com.it.groupware.electronicAppStamp.model.ElectronicAppStampService;
import com.it.groupware.electronicDocFol.model.ElectronicDocFolDTO;
import com.it.groupware.electronicDocFol.model.ElectronicDocFolService;
import com.it.groupware.electronicDocSty.model.ElectronicDocStyDTO;
import com.it.groupware.electronicDocSty.model.ElectronicDocStyService;
import com.it.groupware.electronicFile.model.ElectronicFileDTO;
import com.it.groupware.electronicFile.model.ElectronicFileService;
import com.it.groupware.electronicReLine.model.ElectronicReLineDTO;
import com.it.groupware.electronicReLine.model.ElectronicReLineService;
import com.it.groupware.email.model.EmailDTO;
import com.it.groupware.email.model.EmailService;
import com.it.groupware.emp.model.EmpDTO;
import com.it.groupware.emp.model.EmpService;
import com.it.groupware.index.model.OriVo;
import com.it.groupware.position.model.PositionDTO;
import com.it.groupware.position.model.PositionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.description.modifier.EnumerationState;

@RestController
@RequestMapping("/electronic")
@RequiredArgsConstructor
@Api(tags = "??????")
public class ElectronicController {
	private static final Logger logger = LoggerFactory.getLogger(ElectronicController.class);
	private final ServletContext servletContext;
	private final ImageTranseform itf;
	private final ElectronicDocFolService electronicDocFolService;
	private final ElectronicDocStyService electronicDocStyService;
	private final EmpService empService;
	private final ElectronicService electronicService;
	private final ElectronicAppLineService electronicAppService;
	private final ElectronicReLineService electronicReService;
	private final ElectronicFileService electronicFileService;
	private final ElectronicAppStampService stampService;
	private final DepartmentService departmentService;
	private final PositionService positionService;
	private final EmailService emailService;

	// ????????? ?????? ?????? ?????? ?????? ????????????
	@GetMapping("/sendList")
	@ApiOperation(value = "?????? ?????? ?????? ??????")
	public ResponseEntity<Map<String, Object>> sendList(SearchVO searchVo, int electronicCompletFlag,
			int electronicDraft) {
		Map<String, Object> map = new HashedMap<String, Object>();
		String sortBy = searchVo.getSortBy();
		if (sortBy != null) {
			DataNameTransform dtf = DataNameTransform.valueOfLabel(sortBy);
			searchVo.setSortBy(dtf.name());
		}
		map.put("electronicDraft", electronicDraft);
		map.put("electronicCompletFlag", electronicCompletFlag);
		map.put("searchVo", searchVo);
		Map<String, Object> data = electronicService.sendList(map);
		return new ResponseEntity<Map<String, Object>>(data, HttpStatus.OK);

	}

	// ????????? ?????? ?????? ?????? ?????? ????????????
	@GetMapping("/receiveList")
	@ApiOperation(value = "?????? ?????? ?????? ??????")
	public ResponseEntity<Map<String, Object>> receiveList(SearchVO searchVo, int electronicCompletFlag,
			int approvalLineCompleteFlag) {
		Map<String, Object> map = new HashedMap<String, Object>();
		String sortBy = searchVo.getSortBy();
		if (sortBy != null) {
			DataNameTransform dtf = DataNameTransform.valueOfLabel(sortBy);
			searchVo.setSortBy(dtf.name());
		}

		map.put("approvalLineCompleteFlag", approvalLineCompleteFlag);
		map.put("electronicCompletFlag", electronicCompletFlag);
		map.put("searchVo", searchVo);
		Map<String, Object> data = electronicService.receiveList(map);
		return new ResponseEntity<Map<String, Object>>(data, HttpStatus.OK);

	}

	// ????????? ?????? ?????? ????????? ?????? ?????? ????????????
	@GetMapping("/readList")
	@ApiOperation(value = "?????? ?????? ?????? ??????")
	public ResponseEntity<Map<String, Object>> readList(SearchVO searchVo) {
		Map<String, Object> map = new HashedMap<String, Object>();
		String sortBy = searchVo.getSortBy();
		if (sortBy != null) {
			DataNameTransform dtf = DataNameTransform.valueOfLabel(sortBy);
			searchVo.setSortBy(dtf.name());
		}

		map.put("searchVo", searchVo);
		Map<String, Object> data = electronicService.readList(map);
		return new ResponseEntity<Map<String, Object>>(data, HttpStatus.OK);

	}

	// ?????? ?????? ?????? ?????? ??????
	@GetMapping("/recentlyList")
	@ApiOperation(value = "?????? ?????? ?????? ?????? ??????")
	public ResponseEntity<Map<String, Object>> recentlyList(int empNo) {
		Map<String, Object> data = new HashedMap<String, Object>();
		List<ElectronicRecentlyDTO> list = electronicService.selectRecentlyList(empNo);
		data.put("list", list);
		return new ResponseEntity<Map<String, Object>>(data, HttpStatus.OK);
	}

	// ?????? ????????????
	@PostMapping("/registerSign")
	@ApiOperation(value = "?????? ????????? ?????? ??????")
	public ResponseEntity<String> insertStamp_post(@RequestParam MultipartFile sign, @RequestParam int empNo)
			throws IllegalStateException, IOException {
		int cnt = 0;
		if (sign.getOriginalFilename() != "") {

			ElectronicAppStampDTO appStampDto = new ElectronicAppStampDTO();
			String str = servletContext.getRealPath(ConstUtil.ELECTRONIC_STAMPUP_PATH);
			String fileName = FileUploadUtil.getUniqueFileName(sign.getOriginalFilename());

			sign.transferTo(new File(str + fileName));
			appStampDto.setStampName(fileName);
			appStampDto.setEmpNo(empNo);
			cnt = stampService.registerStamp(appStampDto);

		} // if
		if (cnt == 0)
			throw new CustomException(CustomErrorCode.ELECTRONIC_STAMP_INSERT_FAIL, "?????? ?????? ?????? ??????");
		return new ResponseEntity<>(ConstUtil.SUCCESS, HttpStatus.OK);
	}

	// ?????? ????????????
	@GetMapping("/selectSign")
	@ApiOperation(value = "?????? ????????? ????????????")
	public ResponseEntity<Map<String, Object>> seletStamp(@RequestParam int empNo) throws IOException {
		Map<String, Object> data = new HashedMap<String, Object>();
		String url = servletContext.getRealPath(ConstUtil.ELECTRONIC_STAMPUP_PATH);
		ElectronicAppStampDTO appStampDto = stampService.selectStamp(empNo);

		if (appStampDto == null) {
			throw new CustomException(CustomErrorCode.ELECTRONIC_STAMP_NOT_FIND, "?????? ?????? ????????? ?????? ?????? ?????? ??????");
		} else {
			url += appStampDto.getStampName();

			InputStream imageIS = new FileInputStream(url);
			byte[] imageByteArray = IOUtils.toByteArray(imageIS);
			data.put("image", imageByteArray);
			imageIS.close();

			data.put("url", url);
			data.put("result", ConstUtil.SUCCESS);
			return new ResponseEntity<>(data, HttpStatus.OK);
		}

	}

	// ?????? ?????? and ?????? ????????????
	@GetMapping("/selectFormTree")
	@ApiOperation(value = "?????? ?????? + ?????? ?????? ????????????")
	public ResponseEntity<Map<String, Object>> selectFormTree() {
		Map<String, Object> data = new HashedMap<String, Object>();
		List<Map<String, Object>> treeData = new ArrayList<>();
		List<ElectronicDocFolDTO> folderlist = electronicDocFolService.selectAll();
		if (folderlist != null) {
			for (int i = 0; i < folderlist.size(); i++) {
				Map<String, Object> tree = new HashedMap<String, Object>();
				List<Map<String, Object>> children = new ArrayList<>();
				ElectronicDocFolDTO folderDto = folderlist.get(i);
				int folderNo = folderDto.getFolderNo();
				List<ElectronicDocStyDTO> docStyList = electronicDocStyService.selectByFolderNo(folderNo);

				for (int j = 0; j < docStyList.size(); j++) {
					Map<String, Object> child = new HashMap<>();
					ElectronicDocStyDTO styDto = docStyList.get(j);
					child.put("name", styDto.getStyleName());
					child.put("folderNo", folderNo);
					child.put("folderStyleNo", styDto.getFolderStyleNo());
					children.add(child);
				}

				tree.put("name", folderDto.getFolderName());
				tree.put("children", children);
				treeData.add(tree);
			}

			data.put("treeData", treeData);
			data.put("res", ConstUtil.SUCCESS);
		} else {
			throw new CustomException(CustomErrorCode.ELECTRONIC_FOLDER_NOT_FIND, "?????? ????????? ??????");
		}
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	// ?????? ????????????(??????, ??????)
	@Transactional
	@PostMapping("/registerElimp")
	@ApiOperation(value = "?????? ??????/?????? ??????")
	public ResponseEntity<Map<String, Object>> insertImp(int elNo, @RequestParam(required = false) int[] approver,
			@RequestParam(required = false) int[] receiver, int empNo, String title, @RequestParam String content,
			@RequestParam(required = false) List<MultipartFile> files, int folderStyleNo, int folderNo, int emergency,
			int draft, @RequestParam String empName, int currentDraft) throws Exception {
		logger.info("receiver={}", receiver);
		logger.info("receiver={}", approver);
		logger.info("empNo={}", empNo);
		logger.info("title={}", title);
		logger.info("file={}", files);
		logger.info("content={}", content);
		logger.info("folderStyleNo={}", folderStyleNo);
		logger.info("folderNo={}", folderNo);
		logger.info("empName={}", empName);

		Map<String, Object> data = new HashMap<>();
		int styleNo = electronicDocStyService.selectByFolderStyleNo(folderNo, folderStyleNo);
		ElectronicDocStyDTO styDto = electronicDocStyService.selectByStyleNo(styleNo);

		ElectronicDTO elDto = new ElectronicDTO();
		elDto.setElectronicContent(content);
		elDto.setElectronicDraft(draft);
		elDto.setElectronicEmergencyFlag(emergency);
		elDto.setElectronicFileFlag(files == null ? 0 : 1);
		elDto.setElectronicTitle(title);
		elDto.setEmpName(empName);
		elDto.setEmpNo(empNo);
		elDto.setStyleName(styDto.getStyleName());
		elDto.setStyleNo(styleNo);
		elDto.setElectronicNo(elNo);

		if (currentDraft == 0) {
			int res = electronicService.insertEle(elDto);
			if (res == 0)
				throw new CustomException(CustomErrorCode.ELECTRINIC_INSERT_FAIL, "?????? ?????? ??????");

		} else {
			int res = electronicService.updateEle(elDto);
			if (res == 0)
				throw new CustomException(CustomErrorCode.ELECTRINIC_UPDATE_FAIL, "?????? ?????? ??????");
		}

		if (draft == 0) {
			int electronicNo = elNo;

			if (elNo == 0) {
				electronicNo = electronicService.selectMaxEleNo(empNo);
			}

			if (files != null) {
				String str = servletContext.getRealPath(ConstUtil.ELECTRONIC_FILE_PATH);
				ElectronicFileDTO fileDto = new ElectronicFileDTO();
				for (MultipartFile file : files) {
					String fileName = FileUploadUtil.getUniqueFileName(file.getOriginalFilename());

					file.transferTo(new File(str + fileName));

					fileDto.setFileOriginalname(file.getOriginalFilename());
					fileDto.setFileName(fileName);
					fileDto.setElectronicNo(electronicNo);
					fileDto.setFileSize(file.getSize());
					int res = electronicFileService.insertFile(fileDto);

					if (res == 0)
						throw new CustomException(CustomErrorCode.ELECTRONIC_FILE_INESERT_FAIL, "?????? ?????? ?????? ??????");

				}
			}

			for (int i = 0; i < approver.length; i++) {
				int apempno = approver[i];
				ElectronicAppLineDTO appDto = new ElectronicAppLineDTO();
				appDto.setEmpNo(apempno); // ????????? ??????
				appDto.setApprovalLineOrder(i); // ?????? ??? ?????? ??????
				appDto.setElectronicNo(electronicNo); // ?????? ??????
				if (i == 0) {
					appDto.setApprovalLineCompleteFlag(0);
				} else {
					appDto.setApprovalLineCompleteFlag(3);
				}
				int res = electronicAppService.insertAppLine(appDto);

				if (res == 0)
					throw new CustomException(CustomErrorCode.ELECTRONIC_APPLINE_INESERT_FAIL, "????????? ?????? ??????");

			}

			for (int i = 0; i < receiver.length; i++) {
				int reempno = receiver[i];
				ElectronicReLineDTO rdto = new ElectronicReLineDTO();
				rdto.setEmpNo(reempno);
				rdto.setElectronicNo(electronicNo);
				rdto.setReceiveLineFlag(0);
				int res = electronicReService.insertReLine(rdto);

				if (res == 0)
					throw new CustomException(CustomErrorCode.ELECTRONIC_RELINE_INESERT_FAIL, "????????? ?????? ??????");
			}
			ElectronicRecentlyDTO recentlyrDto = new ElectronicRecentlyDTO();
			recentlyrDto.setEmpNo(empNo);
			recentlyrDto.setStyleName(styDto.getStyleName());
			recentlyrDto.setStyleNo(styleNo);
			int res = electronicService.insertRecently(recentlyrDto);
			if (res == 0) {
				throw new CustomException(CustomErrorCode.ELECTRONIC_RECENTLY_INESERT_FAIL, "????????? ?????? ??????");
			}

		}

		if (elNo == 0) {
			elNo = electronicService.selectMaxEleNo(empNo);
		}
		data.put("electronicNo", elNo);
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	// ?????? ?????? ????????????
	@GetMapping("/detail")
	@ApiOperation(value = "?????? ?????? ??????")
	public ResponseEntity<Map<String, Object>> electronicDetail(int electronicNo, int titleNo, int empNo) {
		Map<String, Object> data = new HashMap<>();
		String url = servletContext.getRealPath(ConstUtil.ELECTRONIC_STAMPUP_PATH);

		// ?????? ????????? ?????? ???????????? ??????
		ElectronicDTO elDto = electronicService.selectByElectronicNo(electronicNo);
		if (elDto == null) {
			throw new CustomException(CustomErrorCode.ELECTRINIC_NOT_FIND, "?????? ????????? ?????? ??????");
		}
		ElectronicDocStyDTO elStyDto = electronicDocStyService.selectByStyleNo(elDto.getStyleNo());
		if (elStyDto == null) {
			throw new CustomException(CustomErrorCode.ELECTRONIC_STYLE_NOT_FIND, "?????? ????????? ?????? ??????");
		}
		// ?????? ????????? ?????? ???????????? ???

		// ??????????????? ?????? ????????? ????????? ???????????? ?????? ??????
		if (titleNo == ConstUtil.ELECTRONIC_SEND_WAIT || titleNo == ConstUtil.ELECTRONIC_SEND_RETURN
				|| titleNo == ConstUtil.ELECTRONIC_SEND_APPROVE) {
			if (elDto.getEmpNo() != empNo) {
				throw new CustomException(CustomErrorCode.ELECTRINIC_EMPNO_NOT_MATCH, "send?????? ????????? ????????? ???????????? ??????");
			} else {
				// ?????? ????????? ????????? ?????? ??????????????? ????????? ????????? ???????????? ??????
				if ((titleNo == ConstUtil.ELECTRONIC_SEND_WAIT && elDto.getElectronicCompletFlag() != 0)
						|| (titleNo == ConstUtil.ELECTRONIC_SEND_RETURN && elDto.getElectronicCompletFlag() != 2)
						|| (titleNo == ConstUtil.ELECTRONIC_SEND_APPROVE && elDto.getElectronicCompletFlag() != 1)) {
					throw new CustomException(CustomErrorCode.ELECTRINIC_STATUS_DISCORD, "??????????????? ?????? ????????? ?????? ?????? ????????? ?????????");
				}
			}
		} else if (titleNo == ConstUtil.ELECTRONIC_RECEIVE_READ) {
			int cnt = electronicReService.selectForCheckExist(electronicNo, empNo);
			if (cnt == 0) {
				throw new CustomException(CustomErrorCode.ELECTRINIC_STATUS_DISCORD, "?????? ????????? ???????????? ???????????? ??????");
			}
		} else if (titleNo == ConstUtil.ELECTRONIC_RECEIVE_APPROVE) {
			int cnt = electronicAppService.selectForCheckExist(electronicNo, empNo, 1);
			if (cnt == 0) {
				throw new CustomException(CustomErrorCode.ELECTRINIC_STATUS_DISCORD, "?????? ????????? ???????????? ??????");
			}
		} 
		else if (titleNo == ConstUtil.ELECTRONIC_RECEIVE_PLAN) {
			int cnt = electronicAppService.selectForCheckExist(electronicNo, empNo, 3);
			if (cnt == 0) {
				throw new CustomException(CustomErrorCode.ELECTRINIC_STATUS_DISCORD, "?????? ????????? ?????? ?????? ??????");
			}
		}
		else if (titleNo == ConstUtil.ELECTRONIC_RECEIVE_RETURN) {
			int cnt = electronicAppService.selectForCheckExist(electronicNo, empNo, 2);
			if (cnt == 0) {
				throw new CustomException(CustomErrorCode.ELECTRINIC_STATUS_DISCORD, "?????? ????????? ?????? ?????? ??????");
			}
		}
		else if (titleNo == ConstUtil.ELECTRONIC_RECEIVE_WAIT) {
			int cnt = electronicAppService.selectForCheckExist(electronicNo, empNo, 0);
			if (cnt == 0) {
				throw new CustomException(CustomErrorCode.ELECTRINIC_STATUS_DISCORD, "?????? ????????? ????????? ?????? ??????");
			}
		}
		// ??????????????? ?????? ????????? ????????? ???????????? ?????? ???

		// ?????? ?????? ???????????? ??????
		List<ElectronicAppLineDTO> appList = electronicAppService.selectByElectronicNo(electronicNo);
		if (appList == null) {
			throw new CustomException(CustomErrorCode.ELECTRONIC_APPLINE_NOT_FIND, "?????? ?????? ????????? ?????? ?????? ?????? ?????? ??????");
		}

		List<Map<String, Object>> approver = new ArrayList<>();
		for (ElectronicAppLineDTO dto : appList) {
			byte[] imageSrc = null;
			Map<String, Object> map = new HashMap<>();

			EmpDTO edto = empService.selectByEmpNo(dto.getEmpNo());
			if (edto == null) {
				throw new CustomException(CustomErrorCode.UserNotFound, "?????? ?????? ?????? ?????? ??????");
			}
			map.put("empName", edto.getEmpName());

			if (dto.getApprovalLineCompleteFlag() == 1) {
				ElectronicAppStampDTO sdto = stampService.selectStamp(dto.getEmpNo());
				if (sdto == null) {
					throw new CustomException(CustomErrorCode.ELECTRONIC_STAMP_NOT_FIND, "?????? ????????? ?????? ?????? ?????? ??????");
				}
				imageSrc = itf.transImage(url + sdto.getStampName());
			} else if (dto.getApprovalLineCompleteFlag() == 2) {
				imageSrc = itf.transImage(url + "stop.png");
			}
			map.put("imageSrc", imageSrc);
			map.put("appLine", dto);

			approver.add(map);
		}
		// ?????? ?????? ???????????? ???

		// ?????? ?????? ?????? ???????????? ??????
		List<ElectronicFileDTO> fileList = electronicFileService.selectFileByEleNo(electronicNo);
		// ?????? ?????? ?????? ???????????? ???

		data.put("fileList", fileList);
		data.put("approver", approver);
		data.put("elDto", elDto);
		data.put("elStyDto", elStyDto);

		return new ResponseEntity<Map<String, Object>>(data, HttpStatus.OK);
	}

	// ?????? ?????? ????????? ?????? ????????????
	@GetMapping("/selectElSty")
	@ApiOperation(value = "?????? ????????? ?????? ??????")
	public ResponseEntity<Map<String, Object>> selectElSty(int styleNo) {
		Map<String, Object> data = new HashMap<>();

		ElectronicDocStyDTO elStyDto = electronicDocStyService.selectByStyleNo(styleNo);
		if (elStyDto == null) {
			throw new CustomException(CustomErrorCode.ELECTRONIC_STYLE_NOT_FIND, "?????? ????????? ?????? ??????");
		}

		data.put("elStyDto", elStyDto);
		return new ResponseEntity<Map<String, Object>>(data, HttpStatus.OK);
	}

	// ???????????? ????????????
	@GetMapping("/draft")
	@ApiOperation(value = "?????? ????????? ?????? ?????? ????????????")
	public ResponseEntity<Map<String, Object>> selectElByDraft(int empNo, int electronicNo) {
		Map<String, Object> data = new HashMap<>();

		ElectronicDTO elDto = electronicService.selectByElectronicNo(electronicNo);

		if (elDto == null) {
			throw new CustomException(CustomErrorCode.ELECTRINIC_NOT_FIND, "?????? ????????? ?????? ?????? ??????");

		} else if (elDto.getEmpNo() != empNo) {
			throw new CustomException(CustomErrorCode.ELECTRINIC_EMPNO_NOT_MATCH, "?????? ?????? ????????? ????????? ????????? ????????? ????????? ?????? ?????????");
		} else if (elDto.getElectronicDraft() != 1) {
			throw new CustomException(CustomErrorCode.ELECTRINIC_IS_NOT_DRATF, "?????? ?????? ????????? ??????????????? ??????");
		}

		data.put("elDto", elDto);
		return new ResponseEntity<Map<String, Object>>(data, HttpStatus.OK);
	}

	// ?????? ???????????? ??????
	@GetMapping("/download")
	@ApiOperation(value = "?????? ?????? ????????????")
	public ResponseEntity<Resource> download(@ModelAttribute("file") ElectronicFileDTO fileDto) throws Exception {
		String str = servletContext.getRealPath(ConstUtil.ELECTRONIC_FILE_PATH) + fileDto.getFileName();

		System.out.println(str);
		File file = new File(str);
		if (!file.exists()) {
			throw new CustomException(CustomErrorCode.ELECTRONIC_FILE_NOT_FIND, "?????? ????????? ??????");
		}

		HttpHeaders header = new HttpHeaders();
		Path fPath = Paths.get(file.getAbsolutePath());

		header.add(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; fileName=" + URLEncoder.encode(fileDto.getFileOriginalname(), "UTF-8"));
		header.add("Cache-Control", "no-cache, no-store, must-revalidate");
		header.add("Pragma", "no-cache");
		header.add("Expires", "0");
		ByteArrayResource rsc = new ByteArrayResource(Files.readAllBytes(fPath));

		return ResponseEntity.ok().headers(header).contentLength(file.length())
				.contentType(MediaType.parseMediaType("application/octet-stream")).body(rsc);
	}

	@Transactional
	@PutMapping("/approve")
	@ApiOperation(value = "?????? ??????")
	public ResponseEntity<String> approve(@RequestBody Map<String, Object> map) {
		int electronicNo = (int) map.get("electronicNo");
		int empNo = (int) map.get("empNo");

		logger.info("electronic={}", electronicNo);
		logger.info("empNo={}", empNo);
		int res = electronicAppService.updateAppLine(electronicNo, empNo, 1);

		if (res == 0) {
			res = electronicService.updateEleComplete(electronicNo, 1);
			if (res == 0) {
				throw new CustomException(CustomErrorCode.ELECTRINIC_UPDATE_FAIL, "?????? ?????? ?????? ??????");
			}
		}

		return new ResponseEntity<String>(ConstUtil.SUCCESS, HttpStatus.OK);

	}

	@Transactional
	@PutMapping("/denied")
	@ApiOperation(value = "?????? ??????")
	public ResponseEntity<String> denied(@RequestBody Map<String, Object> map) {
		int electronicNo = (int) map.get("electronicNo");
		int empNo = (int) map.get("empNo");

		logger.info("electronic={}", electronicNo);
		logger.info("empNo={}", empNo);
		int res = electronicAppService.updateAppLine(electronicNo, empNo, 2);
		res = electronicService.updateEleComplete(electronicNo, 2);
		if (res == 0) {
			throw new CustomException(CustomErrorCode.ELECTRINIC_UPDATE_FAIL, "?????? ?????? ?????? ??????");
		}

		return new ResponseEntity<String>(ConstUtil.SUCCESS, HttpStatus.OK);

	}

//
//	// ?????? ??????
//	@RequestMapping("/AcceptUpdateAppLine")
//	public String AcceptUpdateAppLine(@ModelAttribute ElectronicDTO vo, @RequestParam String no, Model model) {
//		logger.info("????????? ?????? no ={}", no);
//		logger.info("AppLine ???????????? ???????????? electronicVo={}", vo);
//
//		int cnt = 0;
//		ElectronicAppLineDTO evo = null;
//		int uptodate = 0;
//		if (electronicAppService.selectAppLineCheck(vo) != null) {
//			evo = electronicAppService.selectAppLineCheck(vo); // ??? ???????????? ????????? ????????? ????????????
//			logger.info("??? ?????? ?????? ?????? evo.getApprovalLineCompleteFlag()={}", evo.getApprovalLineCompleteFlag());
//
//			if (evo.getApprovalLineCompleteFlag().equals("1")) { // ???????????? ????????? ?????????
//				cnt = electronicAppService.AcceptUpdateAppLine(vo);
//				uptodate = electronicService.upToDate(vo.getElectronicNo());
//			} // ???????????? ????????? ????????????
//
//		} else { // ???????????? ?????????
//			cnt = electronicAppService.AcceptUpdateAppLine(vo);
//			uptodate = electronicService.upToDate(vo.getElectronicNo());
//		}
//		logger.info("?????? ????????? uptodate={}", uptodate);
//
//		List<ElectronicAppLineDTO> list = electronicAppService.selectByElectronicNo(vo.getElectronicNo());
//
//		int sum = 0;
//		for (int i = 0; i < list.size(); i++) {
//			ElectronicAppLineDTO getList = list.get(i);
//			if (getList.getApprovalLineCompleteFlag().equals("1")) {
//				sum += 1;
//			}
//		}
//		int completeCnt = 0;
//		if (sum == list.size()) {
//			completeCnt = electronicService.updateEleComplete(vo.getElectronicNo());
//		}
//
//		String url = "/electronic/electronicList?no=" + no, msg = "?????? ????????? ????????????.";
//		if (cnt > 0) {
//			msg = "?????? ??????";
//		}
//		if (completeCnt > 0) {
//			msg = "?????? ?????? ??????";
//		}
//
//		model.addAttribute("msg", msg);
//		model.addAttribute("url", url);
//		model.addAttribute("navNo", 1);
//
//		return "common/message";
//	}
//

//
//

//
//	// ?????? ?????? ????????? ?????? ??? ?????? ??????
//	@PostMapping("/electronicReturnEmail")
//	public String eleReturnEmail_post(@ModelAttribute EmailDTO emailVo,@RequestParam int ElectronicNo, Model model) {
//		logger.info("?????? ?????? ????????? ???????????? emailVo={}", emailVo);
//		logger.info("?????? ???????????? ???????????? electronicNo ={}", ElectronicNo);
//		
//		int cnt = emailService.sendEmail(emailVo);
//		
//		String url = "/electronic/electronicReturnEmail", msg="?????? ?????? ?????? ??????";
//		if(cnt>0) {
//			msg = "?????? ?????? ?????? ??????";
//		}
//		
//		int cnt2 = electronicService.updateEleReturn(ElectronicNo);
//		
//		if (cnt2 > 0) {
//			msg = "?????? ?????? ??????/ ?????? ?????? ??????";
//		}
//		
//		model.addAttribute("url", url);
//		model.addAttribute("msg", msg);
//		
//		return "common/message";
//	}
//
//	// ????????? ??????
//	@RequestMapping("deleteAppLine")
//	@ResponseBody
//	public int deleteAppLine(@RequestParam(value = "empNo[]") String[] empNo, @RequestParam String electronicNo) {
//		ElectronicAppLineDTO vo = new ElectronicAppLineDTO();
//		vo.setElectronicNo(Integer.parseInt(electronicNo));
//
//		int result = 0;
//		for (int i = 0; i < empNo.length; i++) {
//			logger.info("AppLine ?????? ???????????? empNo={}", empNo[i]);
//			vo.setEmpNo(Integer.parseInt(empNo[i]));
//			int cnt = electronicAppService.deleteAppLine(vo);
//			logger.info("AppLine ?????? ???????????? vo ={}", vo);
//			if (cnt > 0) {
//				logger.info("?????? ??????");
//				result = cnt;
//			}
//		}
//		return result;
//	}
//
//	// ????????? ??????
//	@RequestMapping("deleteReLine")
//	@ResponseBody
//	public int deleteReLine(@RequestParam(value = "empNo[]") String[] empNo, @RequestParam String electronicNo) {
//		ElectronicReLineDTO vo = new ElectronicReLineDTO();
//		vo.setElectronicNo(Integer.parseInt(electronicNo));
//
//		int result = 0;
//		for (int i = 0; i < empNo.length; i++) {
//			logger.info("ReLine ?????? ???????????? empNo={}", empNo[i]);
//			vo.setEmpNo(Integer.parseInt(empNo[i]));
//			int cnt = electronicReService.deleteReLine(vo);
//			logger.info("ReLine ?????? ???????????? vo ={}", vo);
//			if (cnt > 0) {
//				logger.info("?????? ??????");
//				result = cnt;
//			}
//		}
//		return result;
//	}
//
//	// ?????? ??????
//	@RequestMapping("/AcceptUpdateReLine")
//	public String AcceptUpdateReLine(@ModelAttribute ElectronicDTO vo, @RequestParam String no, Model model) {
//		logger.info("????????? ?????? no ={}", no);
//		logger.info("ReLine ???????????? ???????????? electronicVo={}", vo);
//		int cnt = electronicReService.AcceptUpdateReLine(vo);
//		String url = "/electronic/electronicList?no=" + no, msg = "?????? ??????";
//		if (cnt > 0) {
//			msg = "?????? ??????";
//		}
//
//		model.addAttribute("msg", msg);
//		model.addAttribute("url", url);
//		model.addAttribute("navNo", 1);
//
//		return "common/message";
//	}
//

//	// ????????? ?????? ??????
//	@RequestMapping("/deleteFile")
//	@ResponseBody
//	public int deleteFile(@RequestParam int electronicNo) {
//		logger.info("?????? ?????? ?????? ???????????? electronicNo ={}", electronicNo);
//
//		int cnt = electronicFileService.deleteFile(electronicNo);
//
//		return cnt;
//	}

}
