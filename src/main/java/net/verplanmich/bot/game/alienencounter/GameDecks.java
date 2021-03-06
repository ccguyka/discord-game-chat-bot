package net.verplanmich.bot.game.alienencounter;

import net.verplanmich.bot.game.Deck;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

import static net.verplanmich.bot.game.alienencounter.Mission.*;

@Service
public class GameDecks {

    static final String NAME = "alienencounter";

    static final String CHIEF_ENGINEER_PARKER = "chiefengineerparker";
    static final String ENGINEER_BRETT = "engineerbrett";
    static final String EXECUTIV_OFFICER_KANE = "executivofficerkane";
    static final String WARRANT_OFFICER_RIPLEY = "warrantofficerripley";
    static final String NAVIGATOR_LAMBERT = "navigatorlambert";
    static final String CAPTAIN_DALLAS = "captaindallas";

    static final String CHARS = "chars";
    static final String STRIKES = "strikes";
    static final String SERGEANT = "sergeant";
    static final String DRONE = "drone";

    private HashMap<Mission, List<Deck>> crews = new HashMap();
    private HashMap<Mission, List<List<String>>> objectiveCards = new HashMap();
    private HashMap<Integer,HashMap<Integer,Integer>> difficutly = new HashMap();

    private List<String> hiveAndCrew = Arrays.asList("jonesy","newt","missing-brother-red","missing-brother-green","larry-purvis");

    private Deck chars;
    private Deck strikes;
    private Deck sergeant;
    private Deck drone;

    public GameDecks() throws IOException {
        difficutly.put(1,new HashMap());
        difficutly.put(2,new HashMap());
        difficutly.put(3,new HashMap());
        difficutly.put(4,new HashMap());
        difficutly.put(5,new HashMap());
        difficutly.get(1).put(0,0);
        difficutly.get(1).put(1,0);
        difficutly.get(1).put(2,0);
        difficutly.get(2).put(0,0);
        difficutly.get(2).put(1,1);
        difficutly.get(2).put(2,2);
        difficutly.get(3).put(0,2);
        difficutly.get(3).put(1,3);
        difficutly.get(3).put(2,4);
        difficutly.get(4).put(0,4);
        difficutly.get(4).put(1,5);
        difficutly.get(4).put(2,6);
        difficutly.get(5).put(0,4);
        difficutly.get(5).put(1,6);
        difficutly.get(5).put(2,7);
        objectiveCards.put(NOSTROMO,new ArrayList());
        objectiveCards.get(NOSTROMO).add( Arrays.asList("event","event","egg","egg","egg","egg","hazard","game-objective1-sos","game-objective1-sos"));
        objectiveCards.get(NOSTROMO).add( Arrays.asList("jonesy","event","event","objective2-sos","hazard","shedding-skin-xenomorph","shedding-skin-xenomorph","shedding-skin-xenomorph","skittering-xenomorph","skittering-xenomorph","skittering-xenomorph"));
        objectiveCards.get(NOSTROMO).add( Arrays.asList("the-perfect-organism","event","event","hazard","xenomorph-lance-tail","xenomorph-lance-tail","xenomorph-lance-tail","twin-mouth-xenomorph","twin-mouth-xenomorph","twin-mouth-xenomorph","twin-mouth-xenomorph","ash","objective3-sos"));
        objectiveCards.put(SULACI, new ArrayList<>());
        objectiveCards.get(SULACI).add( Arrays.asList("xenomorph-swarmer","xenomorph-swarmer","xenomorph-swarmer","hazard","event","event","colonist-host","colonist-host","colonist-host"));
        objectiveCards.get(SULACI).add( Arrays.asList("hazard","event","event","acid-blood-xenomorph","acid-blood-xenomorph","acid-blood-xenomorph","facehugger","facehugger","newt","sentry-guns","sentry-guns"));
        objectiveCards.get(SULACI).add( Arrays.asList("hazard","event","event","howling-xenomorph","howling-xenomorph","howling-xenomorph","howling-xenomorph","xenomorph-snatcher","xenomorph-snatcher","xenomorph-snatcher","xenomorph-snatcher","carter-burke","the-queen"));
        objectiveCards.put(FURY,new ArrayList());
        objectiveCards.get(FURY).add(Arrays.asList("missing-brother-red","missing-brother-green"));//todo needs scanning
        objectiveCards.get(FURY).add(new ArrayList());
        objectiveCards.get(FURY).add(new ArrayList());
        objectiveCards.put(AURIGA, new ArrayList());
        objectiveCards.get(AURIGA).add(new ArrayList());
        objectiveCards.get(AURIGA).add(new ArrayList());
        objectiveCards.get(AURIGA).add(new ArrayList());


        chars = Deck.getFor(NAME,CHARS);
        strikes = Deck.getFor(NAME,STRIKES);
        sergeant = Deck.getFor(NAME,SERGEANT);
        drone = Deck.getFor(NAME,DRONE);
        crews.put(NOSTROMO,new ArrayList());
        crews.get(NOSTROMO).add(Deck.getFor(NAME, CHIEF_ENGINEER_PARKER));
        crews.get(NOSTROMO).add(Deck.getFor(NAME, ENGINEER_BRETT));
        crews.get(NOSTROMO).add(Deck.getFor(NAME, EXECUTIV_OFFICER_KANE));
        crews.get(NOSTROMO).add(Deck.getFor(NAME, WARRANT_OFFICER_RIPLEY));
        crews.get(NOSTROMO).add(Deck.getFor(NAME, NAVIGATOR_LAMBERT));
        crews.get(NOSTROMO).add(Deck.getFor(NAME, CAPTAIN_DALLAS));
        crews.put(SULACI,new ArrayList());
        crews.get(SULACI).add(new Deck(Arrays.asList("shes-here-as-a-consultant","shes-here-as-a-consultant","shes-here-as-a-consultant","shes-here-as-a-consultant","shes-here-as-a-consultant","power-loader","i-will-never-leave-you","i-will-never-leave-you","i-will-never-leave-you","i-will-never-leave-you","i-will-never-leave-you","ripley-enraged","ripley-enraged","ripley-enraged")));
        crews.get(SULACI).add(new Deck(Arrays.asList("stay-frosty","stay-frosty","stay-frosty","stay-frosty","stay-frosty","close-encounters","close-encounters","close-encounters","close-encounters","close-encounters","lets-go-marines","weapon-training","weapon-training","weapon-training")));
        crews.get(SULACI).add(new Deck(Arrays.asList("split-in-two","knife-games","knife-games","knife-games","critical-analysis","critical-analysis","critical-analysis","critical-analysis","critical-analysis","get-him-to-medical","get-him-to-medical","get-him-to-medical","get-him-to-medical","get-him-to-medical")));
        crews.get(SULACI).add(new Deck(Arrays.asList("they-are-all-around-us","they-are-all-around-us","they-are-all-around-us","they-are-all-around-us","they-are-all-around-us","this-aint-happening-man","body-armor","body-armor","body-armor","you-want-some-of-this","you-want-some-of-this","you-want-some-of-this","you-want-some-of-this","you-want-some-of-this")));
        crews.get(SULACI).add(new Deck(Arrays.asList("wont-give-up","wont-give-up","wont-give-up","by-the-numbers","by-the-numbers","by-the-numbers","by-the-numbers","by-the-numbers","fall-back","fall-back","fall-back","fall-back","fall-back","always-were-an-a-hole")));
        crews.get(SULACI).add(new Deck(Arrays.asList("head-mounted-sight","head-mounted-sight","head-mounted-sight","head-mounted-sight","head-mounted-sight","m56-smartgun","m56-smartgun","m56-smartgun","smartgunners","smartgunners","smartgunners","smartgunners","smartgunners","they-aint-paying-us-enough")));
        crews.put(FURY,new ArrayList());
        crews.get(FURY).add(new Deck(Arrays.asList("the-companys-orders","the-companys-orders","the-companys-orders","the-companys-orders","the-companys-orders","its-his-iq","its-his-iq","its-his-iq","its-his-iq","its-his-iq","internal-exam","internal-exam","internal-exam","buying-time")));
        crews.get(FURY).add(new Deck(Arrays.asList("eulogy","eulogy","eulogy","eulogy","eulogy","i-wanna-get-this-thing","i-wanna-get-this-thing","i-wanna-get-this-thing","re-education","re-education","re-education","re-education","re-education","remember-the-dead")));
        crews.get(FURY).add(new Deck(Arrays.asList("i-want-it-dead-too","i-want-it-dead-too","i-want-it-dead-too","i-want-it-dead-too","i-want-it-dead-too","we-better-rethink-this-thing","we-better-rethink-this-thing","we-better-rethink-this-thing","we-ought-to-panic","we-ought-to-panic","we-ought-to-panic","we-ought-to-panic","we-ought-to-panic","ten-tons-of-hot-lead")));
        crews.get(FURY).add(new Deck(Arrays.asList("bait-for-the-beast","bait-for-the-beast","bait-for-the-beast","bait-for-the-beast","bait-for-the-beast","part-of-the-family","part-of-the-family","part-of-the-family","part-of-the-family","part-of-the-family","whatever-it-takes","whatever-it-takes","whatever-it-takes","ultimate-sacrifice")));
        crews.get(FURY).add(new Deck(Arrays.asList("rumor-control","rumor-control","rumor-control","rumor-control","rumor-control","stop-this-raving","stop-this-raving","stop-this-raving","stop-this-raving","stop-this-raving","were-on-the-honor-system","dont-upset-the-order","dont-upset-the-order","dont-upset-the-order")));
        crews.get(FURY).add(new Deck(Arrays.asList("autopsy","autopsy","autopsy","emergency-surgery","medical-attention","medical-attention","medical-attention","medical-attention","medical-attention","bar-code","bar-code","bar-code","bar-code","bar-code")));

        crews.put(AURIGA, new ArrayList());
        crews.get(AURIGA).add(new Deck(Arrays.asList("kawlang-maneuver","kawlang-maneuver","kawlang-maneuver","gun-for-hire","play-the-angles","play-the-angles","play-the-angles","play-the-angles","play-the-angles","concealed-pistol","concealed-pistol","concealed-pistol","concealed-pistol","concealed-pistol")));
        crews.get(AURIGA).add(new Deck(Arrays.asList("go-it-alone","go-it-alone","go-it-alone","share-a-drink","share-a-drink","share-a-drink","share-a-drink","share-a-drink","shipmates","shipmates","shipmates","shipmates","shipmates","elgyns-crew")));
        //crews.get(AURIGA).add(new Deck(Arrays.asList("i-can-smell-it","i-can-smell-it","i-can-smell-it","i-can-smell-it","i-can-smell-it","the-monsters-mother","new-and-improved","new-and-improved","new-and-improved","new-and-improved","new-and-improved","grown-in-a-lab","grown-in-a-lab","grown-in-a-lab")));
        crews.get(AURIGA).add(new Deck(Arrays.asList("desperate-times","motorized-wheel-chair","motorized-wheel-chair","motorized-wheel-chair","motorized-wheel-chair","motorized-wheel-chair","ill-put-holes-in-you","ill-put-holes-in-you","ill-put-holes-in-you","ill-put-holes-in-you","ill-put-holes-in-you","spare-parts","spare-parts","spare-parts")));
        crews.get(AURIGA).add(new Deck(Arrays.asList("interface-with-fa-th-ur","unlikely-allies","unlikely-allies","unlikely-allies","full-of-surprises","full-of-surprises","full-of-surprises","full-of-surprises","full-of-surprises","secret-mission","secret-mission","secret-mission","secret-mission","secret-mission")));
        crews.get(AURIGA).add(new Deck(Arrays.asList("incinerator-flamer","incinerator-flamer","incinerator-flamer","incinerator-flamer","incinerator-flamer","i-mostly-just-hurt-people","i-mostly-just-hurt-people","i-mostly-just-hurt-people","sleep-when-you-die-man","sleep-when-you-die-man","sleep-when-you-die-man","sleep-when-you-die-man","sleep-when-you-die-man","thermos-gun")));
    }



    public Deck getHive(Mission mission, int players){
        List<String> hiveCards = new ArrayList();
        Deck droneShuffled = new Deck(drone.getDrawPile()).shuffle();
        for(int objective= 0; objective < 3; objective ++){
            List<String> cards = new ArrayList(objectiveCards.get(mission).get(objective));
            IntStream.range(0,difficutly.get(players).get(objective)).forEach(
                    i->cards.add(droneShuffled.drawCard())
            );
            Collections.shuffle(cards);
            hiveCards.addAll(cards);
        }
        return new Deck(hiveCards);
    }

    public Deck barracksFor(Mission mission){
        List<String> barrackCards = new ArrayList();
        List<Deck> nostromoCrew = new ArrayList();
        nostromoCrew.addAll(crews.get(mission));
        Collections.shuffle(nostromoCrew);
        nostromoCrew.stream().limit(4).forEach(
            d-> barrackCards.addAll(d.getDrawPile())
        );
        return new Deck(barrackCards).shuffle();
    }

    public boolean isHiveCardAlsoCrewCard(String cardId) {
        return hiveAndCrew.contains(cardId);
    }

    public Deck getSergeant(){
        return new Deck(sergeant.getDrawPile()).shuffle();
    }

    public Deck getChars(){
        return new Deck(chars.getDrawPile()).shuffle();
    }

    public Deck getStrikes(){
        return new Deck(strikes.getDrawPile()).shuffle();
    }

}
