package com.sean.webcrawler.task;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.utils.FilePersistentBase;

public class PdfFilePipeline extends FilePersistentBase implements Pipeline {
		private Logger logger = LoggerFactory.getLogger(getClass());

		public PdfFilePipeline() {
				setPath("/data/webmagic/");
		}

		public PdfFilePipeline(String path) {
				setPath(path);
		}
		@Override
		public void process(ResultItems resultItems, Task task) {
				List<String> download_link =resultItems.get("download_link");
				List<String> title =resultItems.get("title");
				String path =this.path+PATH_SEPERATOR+task.getUUID()+PATH_SEPERATOR;
				byte[] bytes =new byte[1024*1024*1024];
				if ( download_link.size()>0 && title.size()>0){
						for (int i = 0; i < title.size(); i++) {
								InputStream is=null;
								FileOutputStream fs=null;
								try {
										HttpClient httpClient = HttpClients.createDefault();
										HttpGet get=new HttpGet(download_link.get(i));
										HttpResponse execute = httpClient.execute(get);
										HttpEntity entity = execute.getEntity();
										title.set(i,title.get(i).replace("\\","")
																.replace("/","")
																.replace("\'","")
																.replace("\"","")
																.replace("?","")
																.replace(":","")
																.replace("|","")
																.replace("<","")
																.replace(">",""));
										 is = entity.getContent();
										fs = new FileOutputStream(path + title.get(i) + ".pdf");
//										FileChannel channel = (FileChannel) Channels.newChannel(is);
//										FileChannel channel1 = fs.getChannel();
//										channel1.transferFrom(channel,0,channel.size());


										logger.info(path + title.get(i) + ".pdf");
										int ch = 0;
										while ((ch = is.read(bytes)) != -1) {
												fs.write(bytes,0,ch);
										}
										fs.flush();
								} catch (IOException e) {
										logger.warn(title.get(i));
										logger.warn(download_link.get(i));
										e.printStackTrace();
								}finally {
										if (fs!=null) {
												try {
														fs.close();
												} catch (IOException e) {
														e.printStackTrace();
												}
										}
										if (is!=null) {
												try {
														fs.close();
												} catch (IOException e) {
														e.printStackTrace();
												}
										}
								}
						}
				}
		}
}
