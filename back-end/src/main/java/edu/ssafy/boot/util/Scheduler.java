package edu.ssafy.boot.util;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import edu.ssafy.boot.service.IContentService;
import edu.ssafy.boot.service.IDirectMessageService;
import edu.ssafy.boot.service.IUserService;

@Component
public class Scheduler {

    @Autowired
    @Qualifier("UserService")
    IUserService ser;
    
    @Autowired
    @Qualifier("ContentService")
    IContentService cSer;

    @Autowired
	@Qualifier("DirectMessageService")
    IDirectMessageService dSer;

   @Scheduled(cron = "0 0 0 * * ?")
   public void updateUserStatus() {
    //   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    //   Date now = new Date();
    //   String strDate = sdf.format(now);
    //   System.out.println("Java cron job expression:: " + strDate);
    //   System.out.println("Current Thread : " + Thread.currentThread().getName());
      
      ser.updateUserStatus();
   }

   @Scheduled(cron = "0 0 0 * * ?")
   public void deleteReportedContents() {
      List<String> ImageNames = cSer.deleteReportedContents();

      deleteReportedImages(ImageNames);
   }

   public void deleteReportedImages(List<String> ImageNames){
      // String path = "/upload";
		String realPath = System.getProperty("user.dir")+"\\upload";
		
		// boolean isDelete = true;
		for (String name : ImageNames) {
			String savePath = realPath+File.separator+name;
			File file = new File(savePath);
			if(file.exists()){
				if(file.delete()){
					System.out.println(name + " 삭제 성공");
				}else{
					System.out.println(name + " 삭제 실패");
					// isDelete = false;
				}
			}
		}
   }

   @Scheduled(cron = "0 0 0 * * ?")
   public void deleteMessage(){
      dSer.deleteOldMessage();
   }
}