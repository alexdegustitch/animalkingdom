package com.animals.scheduler.jobs;

import com.animals.scheduler.model.GroupModel;
import com.animals.scheduler.model.StageModel;
import com.animals.scheduler.model.TournamentModel;
import com.animals.scheduler.service.GroupService;
import com.animals.scheduler.service.StageService;
import com.animals.scheduler.service.TournamentService;
import com.animals.scheduler.service.VoteService;
import com.animals.scheduler.utils.ConnectionUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class VoteJob implements Job {

    @Autowired
    private ConnectionUtils connectionUtils;

    @Autowired
    private TournamentService tournamentService;

    @Autowired
    private VoteService voteService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private StageService stageService;


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        List<TournamentModel> activeTournaments = tournamentService.getAllActiveTournaments();
        List<StageModel> stages = new LinkedList<>();
        for (TournamentModel tour : activeTournaments) {
            StageModel stageModel = stageService.getActiveStageForCompetition(tour.getId_competition());
            if (stageModel != null) {
                stages.add(stageModel);
            }
        }

        if (stages.size() == 0)
            return;

        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        String jobEmail = dataMap.getString("type");
        System.out.println("VOTE JOB STARTED " + jobEmail + " : " + jobExecutionContext.getFireTime());
        Map<String, String> mapLoggedInCookies = null;
        if (jobEmail.equals("all")) {
            mapLoggedInCookies = connectionUtils.getArrayList().get(0);
        } else {
            int cnt = connectionUtils.getCnt();
            mapLoggedInCookies = connectionUtils.getArrayList().get(cnt);
            if (cnt == 5) {
                connectionUtils.setCnt(1);
            } else {
                connectionUtils.setCnt(cnt + 1);
            }

        }
        //Connection.Response responseLogin = connectionUtils.getResponseLogin();
        String userAgent = connectionUtils.getUserAgent();
        try {
            String urlExchanges = "https://digitalni-album.igraoni.ca/exchanges?locale=sr&page=1";
            Document doc = Jsoup.connect(urlExchanges)
                    .cookies(mapLoggedInCookies)
                    .userAgent(userAgent)
                    .get();

            Elements elements = doc.select(".user-data");
            int cnt = 1;
            while (elements.size() > 0) {
                cnt++;
                for (Element e : elements) {
                    String offered = e.select("div").get(2).text().split(" ")[1];
                    String wanted = e.select("div").get(3).text().split(" ")[1];
                    int offered_animal = Integer.parseInt(offered.substring(0, offered.length() - 1));
                    int wanted_animal = Integer.parseInt(wanted.substring(0, wanted.length() - 1));
                    String user = e.select("div").get(1).select("a").first().text();
                    // System.out.println(offered_animal + " " + wanted_animal + " " + user);
                    for (StageModel currStage : stages) {
                        GroupModel animalForGroup = groupService.findAnimalByStage(wanted_animal, currStage.getId_stage());
                        if (animalForGroup != null) {
                            connectionUtils.updateVote(user, wanted_animal, offered_animal, currStage.getId_stage(), animalForGroup);
                        }
                    }
                }

                urlExchanges = "https://digitalni-album.igraoni.ca/exchanges?locale=sr&page=" + String.valueOf(cnt);
                doc = Jsoup.connect(urlExchanges)
                        .cookies(mapLoggedInCookies)
                        .userAgent(userAgent)
                        .get();
                elements = doc.select(".user-data");
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
