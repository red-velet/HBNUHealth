/**
 * @Author: SayHello
 * @Date: 2023/3/1 17:42
 * @Introduction:
 */
public class QiNiuCloudTest {
    //@Test
    //public void upload() {
    //    //构造一个带指定 Region 对象的配置类
    //    Configuration cfg = new Configuration(Zone.zone2());
    //
    //    UploadManager uploadManager = new UploadManager(cfg);
    //    //...生成上传凭证，然后准备上传
    //    String accessKey = "Z-QwHcSNyonqJrbu3ai0SbTWbXeXm51q4Ho5-JQw";
    //    String secretKey = "323_o4bcwTg2qMPHmtqN2YkBjMQqyUAiYDtuFHf_";
    //    String bucket = "blue-shawn";
    //    //如果是Windows情况下，格式是 D:\\qiniu\\test.png
    //    String localFilePath = "C:\\Users\\SayHello\\Desktop\\Picture material\\toady is wendy.jpg";
    //    //默认不指定key的情况下，以文件内容的hash值作为文件名
    //    String key = "toady is wendy";
    //
    //    Auth auth = Auth.create(accessKey, secretKey);
    //    String upToken = auth.uploadToken(bucket);
    //
    //    try {
    //        Response response = uploadManager.put(localFilePath, key, upToken);
    //        //解析上传成功的结果
    //        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
    //        System.out.println(putRet.key);
    //        System.out.println(putRet.hash);
    //    } catch (QiniuException ex) {
    //        Response r = ex.response;
    //        System.err.println(r.toString());
    //        try {
    //            System.err.println(r.bodyString());
    //        } catch (QiniuException ex2) {
    //            //ignore
    //        }
    //    }
    //
    //}
    //
    //@Test
    //public void delete() {
    //    //构造一个带指定 Region 对象的配置类
    //    Configuration cfg = new Configuration(Zone.zone2());
    //
    //    UploadManager uploadManager = new UploadManager(cfg);
    //    //...生成上传凭证，然后准备上传
    //    String accessKey = "Z-QwHcSNyonqJrbu3ai0SbTWbXeXm51q4Ho5-JQw";
    //    String secretKey = "323_o4bcwTg2qMPHmtqN2YkBjMQqyUAiYDtuFHf_";
    //    String bucket = "blue-shawn";
    //    //文件名不带文件类型后缀
    //    String key = "toady is wendy";
    //    Auth auth = Auth.create(accessKey, secretKey);
    //    BucketManager bucketManager = new BucketManager(auth, cfg);
    //    try {
    //        //删除空间内的文件
    //        bucketManager.delete(bucket, key);
    //    } catch (QiniuException ex) {
    //        System.err.println(ex.code());
    //        System.err.println(ex.response.toString());
    //    }
    //
    //}
}
