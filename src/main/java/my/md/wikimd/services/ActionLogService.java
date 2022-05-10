package my.md.wikimd.services;

import my.md.wikimd.models.ActionLog;
import my.md.wikimd.repositories.ActionLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ActionLogService {

    @Autowired
    ActionLogRepository actionLogRepository;

    @Transactional
    public ActionLog save(ActionLog actionLog) {
        return actionLogRepository.save(actionLog);
    }

}
