package cn.Joy.service.impl;

import cn.Joy.dao.StudentMapper;
import cn.Joy.entity.Student;
import cn.Joy.service.StudentService;
import cn.Joy.utils.RedisUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StudentServiceImpl implements StudentService {
    @Resource
    private StudentMapper studentMapper;
    @Override
    public Student findBySno(int sno) {
        String studentJson = RedisUtil.getVal(String.valueOf(sno));
        if (studentJson != null && studentJson.length() > 0) {
            return JSONObject.parseObject(studentJson, Student.class);
        } else {
            Student student = studentMapper.findBySno(sno);
            RedisUtil.setVal(String.valueOf(sno), JSONObject.toJSONString(student));
            return student;
        }

    }
}
