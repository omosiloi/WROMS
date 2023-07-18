package edu.scau.mis.system.service;

import edu.scau.mis.system.domain.Log;
import java.util.List;


public interface ILogService {


    Log selectById(Long logId);

    List<Log> selectList(Log log);

    Log insert(Log log);

    Log update(Log log);

    boolean deleteById(Long logId);

}
