package com.mi.elasticsearch.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

/**
 * 背景联查bean类 @Ducument注解里面的indexName代表你的索引名称,type就是文件类型_doc字段
 *
 * @author MichaelShi
 * @email gyechi163.com
 * @date 2019年8月13日 上午9:19:19
 */
@Document(indexName = "info", type = "doc")
public class Info implements Serializable {

    @Id
    private String id;
    private String correlation;
    @Field(type = FieldType.Text, searchAnalyzer = "ik_max_word", analyzer = "ik_smart")
    private String title;
    @Field(type = FieldType.Text, searchAnalyzer = "ik_max_word", analyzer = "ik_smart")
    private String text;
    @Field(type = FieldType.Text, searchAnalyzer = "ik_max_word", analyzer = "ik_smart")
    private String subtitle;
    @Field(type = FieldType.Text, searchAnalyzer = "ik_max_word", analyzer = "ik_smart")
    private String subtitle1;
    @Field(type = FieldType.Text, searchAnalyzer = "ik_max_word", analyzer = "ik_smart")
    private String subtitle2;
    @Field(type = FieldType.Text, searchAnalyzer = "ik_max_word", analyzer = "ik_smart")
    private String subtitle3;
    @Field(type = FieldType.Text, searchAnalyzer = "ik_max_word", analyzer = "ik_smart")
    private String subtitle4;
    private String img;
    private Date date;

    public Info(String id, String correlation, String title, String text, String subtitle, String subtitle1, String subtitle2, String subtitle3, String subtitle4, String img, Date date) {
        this.id = id;
        this.correlation = correlation;
        this.title = title;
        this.text = text;
        this.subtitle = subtitle;
        this.subtitle1 = subtitle1;
        this.subtitle2 = subtitle2;
        this.subtitle3 = subtitle3;
        this.subtitle4 = subtitle4;
        this.img = img;
        this.date = date;
    }

    public Info() {
    }

    @Override
    public String toString() {
        return "Info{" +
                "id='" + id + '\'' +
                ", correlation='" + correlation + '\'' +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", subtitle1='" + subtitle1 + '\'' +
                ", subtitle2='" + subtitle2 + '\'' +
                ", subtitle3='" + subtitle3 + '\'' +
                ", subtitle4='" + subtitle4 + '\'' +
                ", img='" + img + '\'' +
                ", date=" + date +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCorrelation() {
        return correlation;
    }

    public void setCorrelation(String correlation) {
        this.correlation = correlation;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getSubtitle1() {
        return subtitle1;
    }

    public void setSubtitle1(String subtitle1) {
        this.subtitle1 = subtitle1;
    }

    public String getSubtitle2() {
        return subtitle2;
    }

    public void setSubtitle2(String subtitle2) {
        this.subtitle2 = subtitle2;
    }

    public String getSubtitle3() {
        return subtitle3;
    }

    public void setSubtitle3(String subtitle3) {
        this.subtitle3 = subtitle3;
    }

    public String getSubtitle4() {
        return subtitle4;
    }

    public void setSubtitle4(String subtitle4) {
        this.subtitle4 = subtitle4;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
