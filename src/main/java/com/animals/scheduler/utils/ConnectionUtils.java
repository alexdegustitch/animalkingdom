package com.animals.scheduler.utils;


import com.animals.scheduler.model.GroupModel;
import com.animals.scheduler.model.StageModel;
import com.animals.scheduler.model.VoteModel;
import com.animals.scheduler.service.GroupService;
import com.animals.scheduler.service.StageService;
import com.animals.scheduler.service.VoteService;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class ConnectionUtils {

    @Autowired
    private GroupService groupService;
    private int cnt = 1;

    @Autowired
    private VoteService voteService;

    @Autowired
    private StageService stageService;

    private Map<String, String> mapLoggedInCookies;

    private Map<String, Map<String, String>> cookies;
    private ArrayList<Map<String, String>> arrayList;
    private Connection.Response responseLogin;
    private String userAgent;

    private Map<Integer, List<String>> mapGroupNumbers;

    @PostConstruct
    private void init() throws IOException {
        /*
        // log to all accs
        Map<String, String> acc = new HashMap<>();
        acc.put("jez1@gmail.com", "jez");
        //acc.put("jez2@gmail.com", "jez");
        //acc.put("jez3@gmail.com", "jez");
        //acc.put("jez5@gmail.com", "jez");
        acc.put("jez6@gmail.com", "jez");
        //acc.put("jez9@gmail.com", "jez");
        //acc.put("jez10@gmail.com", "jez");
        acc.put("jez11@gmail.com", "jez");
        //acc.put("jez12@gmail.com", "jez");
        acc.put("jez13@gmail.com", "jez");
        //acc.put("jez15@gmail.com", "jez");
        acc.put("jez19@gmail.com", "jez");
        acc.put("jez24@gmail.com", "jez");
        //acc.put("jez25@gmail.com", "jez");

        Connection.Response loginPageResponse = getPageLoginForm();
        cookies = new HashMap<>();
        arrayList = new ArrayList<>();
        for(var entry: acc.entrySet()){
            Map<String, String> cookie = loginToPage(entry.getKey(), entry.getValue());
            cookies.put(entry.getKey(), cookie);
            arrayList.add(cookie);
        }
        */
        mapGroupNumbers = new HashMap<>();
        List<String> list = new ArrayList<>();
        list.add("1423");
        list.add("4132");
        list.add("2314");
        list.add("3241");
        list.add("1423");
        list.add("4132");
        list.add("2314");
        list.add("3241");
        mapGroupNumbers.put(1, list);

        list = new ArrayList<>();
        list.add("121");
        list.add("212");
        list.add("121");
        list.add("212");
        mapGroupNumbers.put(2, list);

        list = new ArrayList<>();
        list.add("1221");
        mapGroupNumbers.put(3, list);
    }
    public Connection.Response getPageLoginForm() throws IOException {
        userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 12_6) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/16.0 Safari/605.1.15";

        //grab login form page first
        Connection.Response loginPageResponse =
                Jsoup.connect("https://digitalni-album.igraoni.ca/login")
                        .referrer("https://digitalni-album.igraoni.ca/")
                        .userAgent(userAgent)
                        .timeout(200000)
                        .followRedirects(true)
                        .execute();

        System.out.println("Fetched login page");
        return loginPageResponse;
    }
    public Map<String, String> loginToPage(String email, String pass) throws IOException {
        String loginUrl = "https://digitalni-album.igraoni.ca/login";

        Connection.Response loginPageResponse = getPageLoginForm();
        //get the cookies from the response, which we will post to the action URL
        Map<String, String> mapLoginPageCookies = loginPageResponse.cookies();

        String authToken = loginPageResponse.parse().select("body > div.content-wrapper > div > div > form > input[type=\"hidden\"]:nth-child(2)")
                .first()
                .attr("value");

        //lets make data map containing all the parameters and its values found in the form
        Map<String, String> mapParams = new HashMap<String, String>();
        mapParams.put("utf8", "e2 9c 93");
        mapParams.put("authenticity_token", authToken);
        mapParams.put("email", email);
        mapParams.put("password", pass);
        mapParams.put("remember", "1");


        mapParams.put("commit", "Prijavi se");


        Connection.Response responsePostLogin = Jsoup.connect(loginUrl)
                //referrer will be the login page's URL
                .referrer(loginUrl)
                //user agent
                .userAgent(userAgent)
                //connect and read time out
                .timeout(2000000)
                //post parameters
                .data(mapParams)
                .method(Connection.Method.POST)
                //cookies received from login page
                .cookies(mapLoginPageCookies)
                //many websites redirects the user after login, so follow them
                .followRedirects(true)
                .execute();

        System.out.println("HTTP Status Code: " + responsePostLogin.statusCode());
        mapLoggedInCookies = responsePostLogin.cookies();
        // responseLogin = responsePostLogin;
        return mapLoggedInCookies;
    }




    @Transactional
    public void updateVote(String user, int wanted_animal, int offered_animal, int stageId, GroupModel animalForGroup){
        VoteModel voteModel = voteService.getVote(wanted_animal, user, stageId);
        if(voteModel == null){
            voteModel = new VoteModel();
            voteModel.setUsername(user);
            voteModel.setOffered_animal(offered_animal);
            voteModel.setWanted_animal(wanted_animal);
            voteModel.setStage(stageId);
            voteModel.setVoting_time(LocalDateTime.now());
            voteService.saveVote(voteModel);

            animalForGroup.setPoints(animalForGroup.getPoints() + 1);
            groupService.updatePoints(animalForGroup);
        }
    }

    public void makeGroups(StageModel currStage){
        switch (currStage.getStage_type()){
            case 1: stage1(currStage); break;
            case 2: stage2(currStage); break;
            case 3: stage3(currStage); break;
            case 4: stage4(currStage); break;
            case 5: stage5(currStage); break;
        }
    }

    private void stage1(StageModel currStage){
        StageModel nextStage = stageService.getStageForCompetitionByStageType(currStage.getCompetition(), currStage.getStage_type() + 1);
        List<String> groupNumbers = mapGroupNumbers.get(1);

        for(int i=1;i<=8;i++)
        {
            List<GroupModel> animals = groupService.groupsForStage(currStage.getId_stage(), i);
            for(int j=0;j<4;j++){
                GroupModel groupModel = animals.get(j);
                groupModel.setPassed(1);
                groupService.saveGroup(groupModel);

                GroupModel newGroup = new GroupModel();
                newGroup.setPassed(0);
                newGroup.setStage(nextStage.getId_stage());
                newGroup.setPoints(0);
                newGroup.setGroup_number(Integer.parseInt(groupNumbers.get(i - 1).substring(j, j + 1)));
                newGroup.setAnimal(groupModel.getAnimal());
                groupService.saveGroup(newGroup);
            }
            for(int j=4;j<animals.size();j++){
                GroupModel groupModel = animals.get(j);
                groupModel.setPassed(-1);
                groupModel.setActive(0);
                groupService.saveGroup(groupModel);
            }
        }
    }
    private void stage2(StageModel currStage){
        StageModel nextStage = stageService.getStageForCompetitionByStageType(currStage.getCompetition(), currStage.getStage_type() + 2);
        StageModel qualificationStage = stageService.getStageForCompetitionByStageType(currStage.getCompetition(), currStage.getStage_type() + 1);
        List<String> groupNumbers = mapGroupNumbers.get(2);

        for(int i=1;i<=4;i++)
        {
            List<GroupModel> animals = groupService.groupsForStage(currStage.getId_stage(), i);
            for(int j=0;j<3;j++){
                GroupModel groupModel = animals.get(j);
                groupModel.setPassed(1);
                groupService.saveGroup(groupModel);

                GroupModel newGroup = new GroupModel();
                newGroup.setPassed(0);
                newGroup.setStage(nextStage.getId_stage());
                newGroup.setPoints(0);
                newGroup.setGroup_number(Integer.parseInt(groupNumbers.get(i - 1).substring(j, j + 1)));
                newGroup.setAnimal(groupModel.getAnimal());
                groupService.saveGroup(newGroup);
            }
            for(int j=3;j<5;j++)
            {
                GroupModel groupModel = animals.get(j);
                groupModel.setPassed(2);
                groupService.saveGroup(groupModel);

                GroupModel newGroup = new GroupModel();
                newGroup.setPassed(0);
                newGroup.setStage(qualificationStage.getId_stage());
                newGroup.setPoints(0);
                newGroup.setGroup_number(1);
                newGroup.setAnimal(groupModel.getAnimal());
                groupService.saveGroup(newGroup);
            }
            for(int j=5;j<animals.size();j++)
            {
                GroupModel groupModel = animals.get(j);
                groupModel.setPassed(-1);
                groupModel.setActive(0);
                groupService.saveGroup(groupModel);
            }
            int lastStageType = currStage.getStage_type() - 1;
            while(lastStageType >= 1){
                StageModel lastStage = stageService.getStageForCompetitionByStageType(currStage.getCompetition(), lastStageType);
                for(int j=5;j<animals.size();j++){
                    GroupModel groupModel = animals.get(j);
                    GroupModel currGroup = groupService.findAnimalByStage(groupModel.getAnimal(), lastStage.getId_stage());
                    if(currGroup == null)
                        continue;
                    currGroup.setActive(0);
                    groupService.saveGroup(currGroup);
                }
                lastStageType--;
            }
        }
    }
    private void stage3(StageModel currStage){
        StageModel nextStage = stageService.getStageForCompetitionByStageType(currStage.getCompetition(), currStage.getStage_type() + 1);
        List<String> groupNumbers = mapGroupNumbers.get(3);

        List<GroupModel> animals = groupService.groupsForStage(currStage.getId_stage(), 1);

        for(int j=0;j<4;j++){
            GroupModel groupModel = animals.get(j);
            groupModel.setPassed(1);
            groupService.saveGroup(groupModel);

            GroupModel newGroup = new GroupModel();
            newGroup.setPassed(0);
            newGroup.setStage(nextStage.getId_stage());
            newGroup.setPoints(0);
            newGroup.setGroup_number(Integer.parseInt(groupNumbers.get(0).substring(j, j + 1)));
            newGroup.setAnimal(groupModel.getAnimal());
            groupService.saveGroup(newGroup);
        }
        int lastStageType = currStage.getStage_type();
        while(lastStageType >= 1){
            StageModel lastStage = stageService.getStageForCompetitionByStageType(currStage.getCompetition(), lastStageType);
            for(int j=4;j<8;j++){
                GroupModel groupModel = animals.get(j);
                GroupModel currGroup = groupService.findAnimalByStage(groupModel.getAnimal(), lastStage.getId_stage());
                if(currGroup == null)
                    continue;
                currGroup.setActive(0);
                groupService.saveGroup(currGroup);
            }
            lastStageType--;
        }

    }
    private void stage4(StageModel currStage){
        StageModel nextStage = stageService.getStageForCompetitionByStageType(currStage.getCompetition(), currStage.getStage_type() + 1);

        for(int i=1;i<=2;i++)
        {
            List<GroupModel> animals = groupService.groupsForStage(currStage.getId_stage(), i);
            for(int j=0;j<4;j++){
                GroupModel groupModel = animals.get(j);
                groupModel.setPassed(1);
                groupService.saveGroup(groupModel);

                GroupModel newGroup = new GroupModel();
                newGroup.setPassed(0);
                newGroup.setStage(nextStage.getId_stage());
                newGroup.setPoints(0);
                newGroup.setGroup_number(1);
                newGroup.setAnimal(groupModel.getAnimal());
                groupService.saveGroup(newGroup);
            }
            int lastStageType = currStage.getStage_type();
            while(lastStageType >= 1){
                StageModel lastStage = stageService.getStageForCompetitionByStageType(currStage.getCompetition(), lastStageType);
                for(int j=4;j<8;j++){
                    GroupModel groupModel = animals.get(j);
                    GroupModel currGroup = groupService.findAnimalByStage(groupModel.getAnimal(), lastStage.getId_stage());
                    if(currGroup == null)
                        continue;
                    currGroup.setActive(0);
                    groupService.saveGroup(currGroup);
                }
                lastStageType--;
            }
        }
    }
    private void stage5(StageModel currStage){

        List<GroupModel> animals = groupService.groupsForStage(currStage.getId_stage(), 1);
        for(int j=0;j<animals.size();j++){
            GroupModel groupModel = animals.get(j);
            groupModel.setPassed(j + 1);
            groupService.saveGroup(groupModel);
        }

        int lastStageType = currStage.getStage_type();
        while(lastStageType >= 1){
            StageModel lastStage = stageService.getStageForCompetitionByStageType(currStage.getCompetition(), lastStageType);
            for(int j=0;j<animals.size();j++){
                GroupModel groupModel = animals.get(j);
                GroupModel currGroup = groupService.findAnimalByStage(groupModel.getAnimal(), lastStage.getId_stage());
                if(currGroup == null)
                    continue;
                currGroup.setActive(0);
                groupService.saveGroup(currGroup);
            }
            lastStageType--;
        }

    }
    public Map<String, String> getMapLoggedInCookies() {
        return mapLoggedInCookies;
    }

    public void setMapLoggedInCookies(Map<String, String> mapLoggedInCookies) {
        this.mapLoggedInCookies = mapLoggedInCookies;
    }

    public Connection.Response getResponseLogin() {
        return responseLogin;
    }

    public void setResponseLogin(Connection.Response responseLogin) {
        this.responseLogin = responseLogin;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public Map<String, Map<String, String>> getCookies() {
        return cookies;
    }

    public void setCookies(Map<String, Map<String, String>> cookies) {
        this.cookies = cookies;
    }

    public ArrayList<Map<String, String>> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<Map<String, String>> arrayList) {
        this.arrayList = arrayList;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }
}
