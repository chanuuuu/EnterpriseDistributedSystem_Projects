package vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class ScheduleTasks {

    @Autowired
    private PollsRepository pollrepo;
    @Autowired
    private ModeratorsRepository modrepo;

    @Scheduled(fixedRate = 3000000)
    public void pollLookUp() {

        String expiryDate = null;

        System.out.println("Scheduler Started!!");
        if (modrepo != null && pollrepo!=null) {

            System.out.println("Inside if condition");

            List poll_list = pollrepo.findAll();
            List mod_list = modrepo.findAll();

            for (int count = 0; count < poll_list.size(); count++) {

                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                String currentDate = date.format(new Date());
                System.out.println(">> CurrentDate:  "+currentDate);
                Polls p_handle = (Polls) poll_list.get(count);
                expiryDate = p_handle.getExpired_at();
                System.out.println(">> Expiry Date:" +expiryDate);


                if(!p_handle.isPollClosed()) {
                    try {

                        Date currentTime = date.parse(currentDate);
                        Date expiryTime = date.parse(expiryDate);

                        System.out.println(">>>> CurrentTime: "+ currentTime + "\n>>> expiryTime "+expiryTime);
                        if (currentTime.after(expiryTime)) {

                            System.out.println("Mod ID:" + p_handle.getModerator_id() +"Poll ID:"+p_handle.getId() + "---> Poll Expired!!!<---");


                            for (int mod_count = 0; mod_count < mod_list.size(); mod_count++) {
                                String mod_email = null;
                                String poll_result = null;
                                Moderators m_handle = (Moderators) mod_list.get(mod_count);

                                if (m_handle.getId().equals(p_handle.getModerator_id())) {
                                    mod_email = m_handle.getEmail();
                                    Integer[] result = p_handle.getResults();

                                    StringBuilder stringBuilder = new StringBuilder();
                                    stringBuilder.append("Poll Result [");

                                    for (int i = 0; i < p_handle.getChoice().length; i++) {

                                        if (i == p_handle.getChoice().length - 1) {

                                            stringBuilder.append(p_handle.getChoice()[i]);
                                            stringBuilder.append("=");
                                            stringBuilder.append(p_handle.getResults()[i]);
                                        } else {
                                            stringBuilder.append(p_handle.getChoice()[i]);
                                            stringBuilder.append("=");
                                            stringBuilder.append(p_handle.getResults()[i]);
                                            stringBuilder.append(",");
                                        }
                                    }
                                    stringBuilder.append("]");

                                    poll_result = stringBuilder.toString();
                                    System.out.println(mod_email + ":010039120:" + poll_result);
                                    SimpleProducer producer = new SimpleProducer();
                                    System.out.println("Call Producer!!");
                                    producer.sendEmail(mod_email,poll_result);
                                }
                            }

                            p_handle.setPollClosed(true);
                            pollrepo.save(p_handle);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }
}
