package cn.itcast;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;

public class IndexManager {
//    创建索引文件
    @Test
    public void testCreateIndex() throws Exception{
//        D:\class101\index-lucene'
//        指定索引库的位置
        Directory directory = FSDirectory.open(new File("D:\\class101\\index-lucene"));
//        Version matchVersion, Analyzer analyzer
//        Analyzer analyzer = new StandardAnalyzer()
       // 创建分词器
        Analyzer analyzer = new IKAnalyzer();
//        配置版本和分词器
        IndexWriterConfig config = new IndexWriterConfig(Version.LATEST,analyzer);
        //        Directory d, IndexWriterConfig conf
//        创建用于存储索引对象的indexWriter
        IndexWriter indexWriter = new IndexWriter(directory,config);
        indexWriter.deleteAll();//删除索引库
//        读取文件夹
        File fileDirectoryPath = new File("D:\\ITCAST\\新版电商前置课\\day02_lucene\\资料\\上课用的查询资料searchsource");
//        获取文件夹中的文件
        File[] files = fileDirectoryPath.listFiles();
        for (File file : files) {
            Document document = new Document();
//            文件名 fileName
            String fileName = file.getName();
            document.add(new TextField("fileName",fileName, Field.Store.YES)); //是否存储原内容
//            内容fileContent
            String fileContent = FileUtils.readFileToString(file, "utf-8");
            document.add(new TextField("fileContent",fileContent, Field.Store.YES));
//            文件大小 fileSize
            long fileSize = FileUtils.sizeOf(file);//单位是byte字节
            document.add(new LongField("fileSize",fileSize, Field.Store.YES));
//            文件路径 filePath
            String filePath = file.getPath();
            document.add(new StringField("filePath",filePath, Field.Store.YES));
            indexWriter.addDocument(document);
        }
        indexWriter.close();
    }

    @Test
    public void testCreateIndexOne() throws Exception{
//        指定索引库的位置
        Directory directory = FSDirectory.open(new File("D:\\class101\\index-lucene"));
//        Version matchVersion, Analyzer analyzer
//        Analyzer analyzer = new StandardAnalyzer()
        // 创建分词器
        Analyzer analyzer = new IKAnalyzer();
//        配置版本和分词器
        IndexWriterConfig config = new IndexWriterConfig(Version.LATEST,analyzer);
        //        Directory d, IndexWriterConfig conf
//        创建用于存储索引对象的indexWriter
        IndexWriter indexWriter = new IndexWriter(directory,config);
//        读取文件夹
        File fileDirectoryPath = new File("D:\\ITCAST\\新版电商前置课\\day02_lucene\\资料\\上课用的查询资料searchsource");
//        获取文件夹中的文件
            Document document = new Document();
//            文件名 fileName
        TextField textField = new TextField("fileName", "spring是一个工作中不可少的非常优秀的后台开发框架", Field.Store.YES);
        textField.setBoost(10);
        document.add(textField); //是否存储原内容
//            内容fileContent
            document.add(new TextField("fileContent","spring是一个工作中不可少的框架spring是一个工作中不可少的框架", Field.Store.YES));
//            文件大小 fileSize
            document.add(new LongField("fileSize",100L, Field.Store.YES));
//            文件路径 filePath
            document.add(new StringField("filePath","SSS", Field.Store.YES));
            indexWriter.addDocument(document);
        indexWriter.close();
    }



    @Test
    public void testSearch() throws Exception{
//        指定索引路径
        Directory directory = FSDirectory.open(new File("D:\\class101\\index-lucene"));
//        创建用户读取索引的对象
        IndexReader indexReader = DirectoryReader.open(directory);
//    创建用来查询的对象
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
//        使用term查询
//        Query query = new TermQuery(new Term("fileName","简介"));

//        查询所有
//        Query query = new MatchAllDocsQuery();

//        通配符查询
//        Query query = new WildcardQuery(new Term("fileName","*简*"));

//        组合查询
//        名称中带apache并且内容中带spring
//        BooleanQuery query = new BooleanQuery();
//        query.add(new TermQuery(new Term("fileName","apache")), BooleanClause.Occur.MUST);
//        query.add(new TermQuery(new Term("fileContent","apache")),BooleanClause.Occur.SHOULD);

//        区间查询
//        Query query = NumericRangeQuery.newLongRange("fileSize",100L,500L,true,true);



        //        使用term查询
//        Query query = new TermQuery(new Term("fileContent","spring is a good project"));

//        单域的分词查询
//        QueryParser queryParser = new QueryParser("fileName",new IKAnalyzer());
//        Query query = queryParser.parse("spring is a good project");

//        多域的分词查询
//        QueryParser queryParser = new MultiFieldQueryParser(new String[]{"fileName","fileContent"},new IKAnalyzer());
//        Query query = queryParser.parse("spring is a good project");


        Query query = new TermQuery(new Term("fileName","spring"));
        TopDocs topDocs = indexSearcher.search(query, 100);//100 最多显示100条数据
//        topDocs.totalHits
        System.out.println("符合查询条件的总条数："+topDocs.totalHits);
        ScoreDoc[] scoreDocs = topDocs.scoreDocs; //获取所有命中的文档对象
        for (ScoreDoc scoreDoc : scoreDocs) {
            int docID = scoreDoc.doc;   //获取文档id
            Document document = indexSearcher.doc(docID);  //根据id查询对象
            System.out.println( "fileName:"+ document.get("fileName"));
            System.out.println( "fileSize:"+ document.get("fileSize"));
            System.out.println( "filePath:"+ document.get("filePath"));
//            System.out.println( "fileContent:"+ document.get("fileContent"));
            System.out.println("------------------------------------------------------------------------");
        }
        indexReader.close();
    }

//    验证分词器的效果
    @Test
    public void testAnalyzer() throws Exception{
//        Analyzer analyzer = new StandardAnalyzer();
//        Analyzer analyzer = new CJKAnalyzer();
//        Analyzer analyzer = new SmartChineseAnalyzer();
        Analyzer analyzer = new IKAnalyzer();

//        String text ="The Spring Framework provides a comprehensive programming and configuration model.";
        String text ="ElasticSearch他妈的简介教程_大数据入门到实战-在线免费直播课Lucene";
        TokenStream tokenStream = analyzer.tokenStream("test", text);
        CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
        tokenStream.reset(); //指针复位
        while (tokenStream.incrementToken()){
            System.out.println(charTermAttribute);
        }


    }


}
