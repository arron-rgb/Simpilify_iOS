package neu.edu.info6350.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import neu.edu.info6350.model.Group;
import neu.edu.info6350.repository.GroupMapper;
import neu.edu.info6350.service.GroupService;

/**
 * @author arronshentu
 */
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, Group> implements GroupService {}
