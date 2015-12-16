package com.sttri.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.sttri.bean.PageUtil;
import com.sttri.bean.QueryJSON;
import com.sttri.entity.Admin;
import com.sttri.entity.File;
import com.sttri.entity.FileCriteria;
import com.sttri.service.IAdminService;
import com.sttri.service.IFileService;
import com.sttri.utils.JacksonUtil;

@Controller
@RequestMapping("/file")
public class FileController extends BaseController {
	@Autowired
	private IFileService fileService;
	@Autowired
	private IAdminService adminService;
	
	@RequestMapping("/toFile")
	public String toFile(){
		return "/file/file";
	}
	@RequestMapping("/list" )
	public String queryFile(ModelMap map,@RequestParam(required = false) String page,
			@RequestParam(required = false) String rows,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String startTime,
			@RequestParam(required = false) String endTime,HttpServletRequest request,HttpServletResponse response ) {

		int start = Integer.parseInt((page == "0" || page == null) ? "1":page);
		int limitSize = Integer.parseInt((rows == "0" || rows == null) ? "10":rows);
		map.addAttribute("name", name);
		map.addAttribute("start", start);
		map.addAttribute("limitSize", limitSize);
		map.addAttribute("startTime", startTime);
		map.addAttribute("endTime", endTime);
		try {
			PrintWriter pw = response.getWriter();
			PageUtil<com.sttri.entity.File> pUtil = this.fileService.getFileByPage(limitSize, start,name, startTime, endTime);
			if(pUtil != null && pUtil.getDatas().size() > 0){
				QueryJSON  qu = new QueryJSON();
				qu.setTotal(pUtil.getTotalCount());
				qu.setRows(pUtil.getDatas());
				System.out.println(JacksonUtil.objectToJson(qu));
				pw.print(JacksonUtil.objectToJson(qu));
			}else{
				String json = "{\"total\":1,\"rows\":[{\"filename\":\"无记录数据\"}]}";
				pw.print(json);
			}
			pw.flush();
			pw.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @param map
	 * @param File
	 * @return 1-成功 2-账号已存在 ，其他-失败
	 */
	@RequestMapping("/save")
	public void save(ModelMap map,@ModelAttribute File file,HttpServletRequest request,HttpServletResponse response){
		int result = -1;
		try {
			PrintWriter pw = response.getWriter();
			FileCriteria criteria = new FileCriteria();
			criteria.createCriteria().andFilenameEqualTo(file.getFilename());
			List<File> list = this.fileService.selectAll(criteria);
			if (list != null && list.size() > 0 ) {
				result = 2;
			}else {
				result = this.fileService.save(file);
			}
			pw.print(result);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param map
	 * @param File
	 * @return 1-成功 2-账号不存在 ，其他-失败
	 */
	@RequestMapping("/update")
	public void update(ModelMap map,@ModelAttribute File file,HttpServletRequest request,HttpServletResponse response){
		int result = 0;
		try {
			PrintWriter pw = response.getWriter();
			int id = file.getId();
			File oFile = this.fileService.queryFileById(id);
			if (oFile != null) {
				file.setUploadtime(oFile.getUploadtime());
				result = this.fileService.update(file);
			}else {
				result = 2;
			}
			pw.print(result);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据ID查询
	 * @param map
	 * @param id
	 * @return
	 */
	@RequestMapping("getbyid")
	public void getbyid(ModelMap map,@RequestParam String id,HttpServletRequest request,HttpServletResponse response){
		try {
			PrintWriter pw = response.getWriter();
			File File = this.fileService.queryFileById(Integer.parseInt(id));
			pw.print(JacksonUtil.objectToJson(File));
			pw.flush();
			pw.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除账号
	 * @param map
	 * @param id
	 * @return 0-失败 1-成功 ，其他-失败
	 */
	@RequestMapping("/delete")
	public void delete(ModelMap map,@RequestParam(required=true) String id,HttpServletRequest request,HttpServletResponse response){
		int result = 0;
		try {
			result = this.fileService.delete(Integer.parseInt(id));
			PrintWriter pw = response.getWriter();
			pw.print(result);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/toUpload")
	public String toUpload(ModelMap map,HttpServletRequest request,HttpServletResponse response){
		String account = getLoginAccount(request, response);
		map.addAttribute("Account", account);
		return "/upload";
	}
	
	@RequestMapping("/upload")  
    public String upload(HttpServletRequest request,HttpServletResponse response) throws IllegalStateException, IOException { 
        //创建一个通用的多部分解析器  
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
        //判断 request 是否有文件上传,即多部分请求  
        if(multipartResolver.isMultipart(request)){  
            //转换成多部分request    
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
            //取得request中的所有文件名  
            Iterator<String> iter = multiRequest.getFileNames();  
            while(iter.hasNext()){  
                //取得上传文件  
                MultipartFile file = multiRequest.getFile(iter.next());  
                if(file != null){  
                    //取得当前上传文件的文件名称  
                    String myFileName = file.getOriginalFilename();  
                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
                    if(myFileName.trim() !=""){  
                        System.out.println(myFileName);  
                        //重命名上传后的文件名  
                        //String fileName = "demoUpload" + file.getOriginalFilename();  
                    	// 得到文件的扩展名(无扩展名时将得到全名)
            			/*String t_ext = myFileName.substring(myFileName.lastIndexOf(".") + 1);
            			 // 允许上传的文件格式的列表
                		final String allowedExt = "jpg,gif,png,swf,flv,iso,img,ua,mp4,rmvb,txt";
            			// 拒绝接受规定文件格式之外的文件类型
            			if(allowedExt.indexOf(t_ext.toLowerCase())==-1){
            				return "false";
            			}*/
            			//保存的具体路径
                		String sFilePath = request.getSession().getServletContext().getRealPath("/");//文件保存的绝对路径
                        //String sFilePath = request.getSession().getServletContext().getContextPath();//文件保存的相对路径
                		// 实例化一个硬盘文件工厂,用来配置上传组件ServletFileUpload
                		DiskFileItemFactory dfif = new DiskFileItemFactory();
                		dfif.setSizeThreshold(4096);// 设置上传文件时用于临时存放文件的内存大小,这里是4K.多于的部分将临时存在硬盘
                		String uploadpath ="/uploadfiles/"+Calendar.getInstance().get(Calendar.YEAR)+"-"+(Calendar.getInstance().get(Calendar.MONTH)+1)+"-"+Calendar.getInstance().get(Calendar.DATE);
                		//String uploadpath = "D:/" + fileName;  
                		java.io.File localFile = new java.io.File(sFilePath+uploadpath+"/"+myFileName);
                		if(!localFile.exists()){
                			localFile.mkdirs();
                		}
                		// 设置存放临时文件的目录,web根目录下的uploadfiles目录
                		dfif.setRepository(localFile);
                        file.transferTo(localFile); 
                        String account = getLoginAccount(request, response);
                        if (account !=null && !"".equals(account)) {
							Admin admin = this.adminService.queryAdminByAccount(account);
							File oFile = new File();
							oFile.setUid(admin.getId());
	                        oFile.setFilename(myFileName);
	                        oFile.setFilepath(uploadpath+"/"+myFileName);
	                        oFile.setFlag(0);
	                        oFile.setPraises(0);
	                        oFile.setStatus(0);
	                        oFile.setSubject(myFileName);
	                        oFile.setSummary("");
	                        this.fileService.save(oFile);
						}
                    }  
                }  
            }  
              
        }  
        return "redirect:/file/toFile";
    }
	 
}
