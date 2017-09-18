package com.moxl.movie.utils;

import java.io.IOException;

import java.math.BigDecimal;
import java.util.UUID;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Files;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.impl.AdaptorErrorContext;
import org.nutz.mvc.upload.TempFile;
import org.nutz.mvc.upload.UploadAdaptor;

import com.moxl.movie.nutz.LoginFilter;

@IocBean
@At("/file")
@Filters({@By(type=LoginFilter.class)})
public class FileSrv {
    @Inject
    protected UploadAdaptor upload;
    /**
     * 根据异常提示错误信息
     * @param t
     */
    private String errorMsg(Throwable t) {
        if (t == null || t.getClass() == null) {
            return "错误：未知system错误！";
        } else {
            String className = t.getClass().getSimpleName();
            if (className.equals("UploadUnsupportedFileNameException")) {
                String name = upload.getContext().getNameFilter();
                return "错误：无效的文件扩展名，支持的扩展名：" + name.substring(name.indexOf("(") + 1, name.lastIndexOf(")")).replace("|", ",");
            } else if (className.equals("UploadUnsupportedFileTypeException")) {
                return "错误：不支持的文件类型！";
            } else if (className.equals("UploadOutOfSizeException")) {
                return "错误：文件超出" + getFileSize(upload.getContext().getMaxFileSize(), 2) + "MB";
            } else if (className.equals("UploadStopException")) {
                return "错误：上传中断！";
            } else {
                return "错误：未知错误！";
            }
        }
    }
    public String jsonString(String str)
    {
        str = str.replace("\\", "\\\\");
        str = str.replace("/", "\\/");
        str = str.replace("'", "\\'");
        return str;
    }
    /**
     * 返回文件大小，单位MB
     * @param filesize
     * @param scale,小数位数
     * @return
     */
    private double getFileSize(long filesize, int scale) {
        BigDecimal bd1 = new BigDecimal(Long.toString(filesize));
        BigDecimal bd2 = new BigDecimal(Long.toString(1024));
        return bd1.divide(bd2, scale, BigDecimal.ROUND_HALF_UP).divide(bd2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
  //{"${app.root}/temp", "8192", "utf-8", "200", "10485760"}
  //参数: 临时文件夹路径,缓存环的大小,编码,临时文件夹的文件数,上传文件的最大大小
    @AdaptBy(type = UploadAdaptor.class, args ="ioc:upload")
    @POST
   @At
    @Ok("raw")
    @Fail("void")
    public String upload(@Param("filedata") TempFile tmpFile,AdaptorErrorContext errCtx) {
    	String err = "";
        String msg = "''";  
        if(tmpFile==null){
        return "{'err':'" + jsonString("空文件") + "','msg':" + msg + "}";
        }
        /* //抛出异常后，不能返回前端*/
        if (errCtx != null && errCtx.getAdaptorErr() != null) {
              err=errorMsg(errCtx.getAdaptorErr());
            return "{'err':'" + jsonString(err) + "','msg':" + msg + "}";
            }
        String filename = tmpFile.getSubmittedFileName();//本地文件名
        long filelen=tmpFile.getSize();//文件大小
        long maxfsize=10485760;//限制上传的文件大小<=10MB
        if(filelen>maxfsize)return "{'err':'" + jsonString("文件超出" + getFileSize(maxfsize, 2) + "MB") + "','msg':" + msg + "}";
 
        String suffixname =tmpFile.getMeta().getFileExtension();
        String date = DateUtil.getToday();//.replace("-", "/");
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");//32位十六进制小写
        String fname = uuid + suffixname;
        String newfilepath = Mvcs.getServletContext().getRealPath("/upload/" +  date+ "/");
        Files.createDirIfNoExists(newfilepath);
        String dest = newfilepath+ "/"+fname;//目标路径，含文件名
        try {
        tmpFile.write(dest);//存储到服务器Disk
        } catch (IOException e) {
            e.printStackTrace();
            err="错误：文件服务器IO异常！";
            return "{'err':'" + jsonString(err) + "','msg':" + msg + "}";
        }
 
        String target="upload/" + date + "/" + fname;
        msg="{'url':'"+target+"','localname':'"+jsonString(filename)+"','id':'1'}";
        err="";
        return  "{'err':'" + jsonString(err) + "','msg':" + msg + "}";
    }
    
}
