package com.example.quizmaster;

import android.content.Context;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QuizManager {
    private List<Question> allQuestions;

    public QuizManager(Context context) {
        allQuestions = new ArrayList<>();
        loadQuestions();
    }

    private void loadQuestions() {
        // ========================================
        // GENERAL KNOWLEDGE QUESTIONS
        // ========================================

        // EASY (10 questions)
        allQuestions.add(new Question("What is the capital of France?",
                Arrays.asList("London", "Berlin", "Paris", "Madrid"), 2, "general", "easy"));
        allQuestions.add(new Question("How many continents are there?",
                Arrays.asList("5", "6", "7", "8"), 2, "general", "easy"));
        allQuestions.add(new Question("What color is the sky on a clear day?",
                Arrays.asList("Blue", "Green", "Red", "Yellow"), 0, "general", "easy"));
        allQuestions.add(new Question("How many days are in a week?",
                Arrays.asList("5", "6", "7", "8"), 2, "general", "easy"));
        allQuestions.add(new Question("What is the opposite of hot?",
                Arrays.asList("Warm", "Cold", "Cool", "Freezing"), 1, "general", "easy"));
        allQuestions.add(new Question("How many hours are in a day?",
                Arrays.asList("12", "20", "24", "30"), 2, "general", "easy"));
        allQuestions.add(new Question("What comes after Monday?",
                Arrays.asList("Wednesday", "Tuesday", "Sunday", "Friday"), 1, "general", "easy"));
        allQuestions.add(new Question("How many legs does a spider have?",
                Arrays.asList("6", "8", "10", "12"), 1, "general", "easy"));
        allQuestions.add(new Question("What do bees make?",
                Arrays.asList("Milk", "Honey", "Jam", "Butter"), 1, "general", "easy"));
        allQuestions.add(new Question("What is the largest planet in our solar system?",
                Arrays.asList("Earth", "Mars", "Jupiter", "Saturn"), 2, "general", "easy"));

        // MEDIUM (10 questions)
        allQuestions.add(new Question("What is the largest ocean on Earth?",
                Arrays.asList("Atlantic", "Indian", "Arctic", "Pacific"), 3, "general", "medium"));
        allQuestions.add(new Question("Who painted the Mona Lisa?",
                Arrays.asList("Van Gogh", "Picasso", "Da Vinci", "Monet"), 2, "general", "medium"));
        allQuestions.add(new Question("What is the currency of Japan?",
                Arrays.asList("Yuan", "Won", "Yen", "Rupee"), 2, "general", "medium"));
        allQuestions.add(new Question("How many sides does a hexagon have?",
                Arrays.asList("5", "6", "7", "8"), 1, "general", "medium"));
        allQuestions.add(new Question("What is the capital of Canada?",
                Arrays.asList("Toronto", "Vancouver", "Ottawa", "Montreal"), 2, "general", "medium"));
        allQuestions.add(new Question("Which language is spoken in Brazil?",
                Arrays.asList("Spanish", "Portuguese", "French", "Italian"), 1, "general", "medium"));
        allQuestions.add(new Question("What is the tallest mountain in the world?",
                Arrays.asList("K2", "Mount Everest", "Kilimanjaro", "Denali"), 1, "general", "medium"));
        allQuestions.add(new Question("How many strings does a standard guitar have?",
                Arrays.asList("4", "5", "6", "7"), 2, "general", "medium"));
        allQuestions.add(new Question("What is the largest desert in the world?",
                Arrays.asList("Sahara", "Arabian", "Gobi", "Kalahari"), 0, "general", "medium"));
        allQuestions.add(new Question("Which ocean is Bermuda located in?",
                Arrays.asList("Pacific", "Atlantic", "Indian", "Arctic"), 1, "general", "medium"));

        // HARD (10 questions)
        allQuestions.add(new Question("What is the smallest country in the world?",
                Arrays.asList("Monaco", "Vatican City", "San Marino", "Liechtenstein"), 1, "general", "hard"));
        allQuestions.add(new Question("In which year was the United Nations founded?",
                Arrays.asList("1942", "1945", "1948", "1950"), 1, "general", "hard"));
        allQuestions.add(new Question("What is the longest river in South America?",
                Arrays.asList("Amazon", "Paraná", "Orinoco", "São Francisco"), 0, "general", "hard"));
        allQuestions.add(new Question("Which element has the chemical symbol 'Au'?",
                Arrays.asList("Silver", "Gold", "Aluminum", "Copper"), 1, "general", "hard"));
        allQuestions.add(new Question("What is the capital of New Zealand?",
                Arrays.asList("Auckland", "Wellington", "Christchurch", "Hamilton"), 1, "general", "hard"));
        allQuestions.add(new Question("How many time zones does Russia have?",
                Arrays.asList("7", "9", "11", "13"), 2, "general", "hard"));
        allQuestions.add(new Question("What is the rarest blood type?",
                Arrays.asList("O negative", "AB positive", "AB negative", "B negative"), 2, "general", "hard"));
        allQuestions.add(new Question("Which Shakespeare play features the character Prospero?",
                Arrays.asList("Hamlet", "Macbeth", "The Tempest", "Othello"), 2, "general", "hard"));
        allQuestions.add(new Question("What is the only continent without an active volcano?",
                Arrays.asList("Europe", "Australia", "Antarctica", "Asia"), 1, "general", "hard"));
        allQuestions.add(new Question("In which year did the Titanic sink?",
                Arrays.asList("1910", "1912", "1914", "1916"), 1, "general", "hard"));

        // ========================================
        // SCIENCE QUESTIONS
        // ========================================

        // EASY (10 questions)
        allQuestions.add(new Question("What is H2O?",
                Arrays.asList("Oxygen", "Hydrogen", "Water", "Helium"), 2, "science", "easy"));
        allQuestions.add(new Question("How many planets are in our solar system?",
                Arrays.asList("7", "8", "9", "10"), 1, "science", "easy"));
        allQuestions.add(new Question("What do plants need to make food?",
                Arrays.asList("Sunlight", "Moonlight", "Stars", "Darkness"), 0, "science", "easy"));
        allQuestions.add(new Question("What gas do plants give off?",
                Arrays.asList("Carbon Dioxide", "Oxygen", "Nitrogen", "Helium"), 1, "science", "easy"));
        allQuestions.add(new Question("What force pulls things to the ground?",
                Arrays.asList("Magnetism", "Electricity", "Gravity", "Friction"), 2, "science", "easy"));
        allQuestions.add(new Question("What is the center of an atom called?",
                Arrays.asList("Electron", "Proton", "Nucleus", "Neutron"), 2, "science", "easy"));
        allQuestions.add(new Question("What is the boiling point of water?",
                Arrays.asList("50°C", "100°C", "150°C", "200°C"), 1, "science", "easy"));
        allQuestions.add(new Question("What planet is known as the Red Planet?",
                Arrays.asList("Venus", "Mars", "Jupiter", "Saturn"), 1, "science", "easy"));
        allQuestions.add(new Question("How many bones are in the human body?",
                Arrays.asList("106", "206", "306", "406"), 1, "science", "easy"));
        allQuestions.add(new Question("What is the largest land animal?",
                Arrays.asList("Giraffe", "Rhino", "Elephant", "Hippo"), 2, "science", "easy"));

        // MEDIUM (10 questions)
        allQuestions.add(new Question("What is the chemical symbol for gold?",
                Arrays.asList("Go", "Gd", "Au", "Ag"), 2, "science", "medium"));
        allQuestions.add(new Question("What is the largest organ in the human body?",
                Arrays.asList("Heart", "Brain", "Liver", "Skin"), 3, "science", "medium"));
        allQuestions.add(new Question("What type of animal is a Komodo dragon?",
                Arrays.asList("Snake", "Lizard", "Crocodile", "Dinosaur"), 1, "science", "medium"));
        allQuestions.add(new Question("What is the powerhouse of the cell?",
                Arrays.asList("Nucleus", "Ribosome", "Mitochondria", "Chloroplast"), 2, "science", "medium"));
        allQuestions.add(new Question("How many chambers does a human heart have?",
                Arrays.asList("2", "3", "4", "5"), 2, "science", "medium"));
        allQuestions.add(new Question("What is the hardest natural substance?",
                Arrays.asList("Gold", "Iron", "Diamond", "Platinum"), 2, "science", "medium"));
        allQuestions.add(new Question("What is the study of weather called?",
                Arrays.asList("Geology", "Meteorology", "Astronomy", "Biology"), 1, "science", "medium"));
        allQuestions.add(new Question("How long does it take for light from the Sun to reach Earth?",
                Arrays.asList("8 seconds", "8 minutes", "8 hours", "8 days"), 1, "science", "medium"));
        allQuestions.add(new Question("What is the freezing point of water in Celsius?",
                Arrays.asList("-10°C", "0°C", "10°C", "32°C"), 1, "science", "medium"));
        allQuestions.add(new Question("What blood type is the universal donor?",
                Arrays.asList("A", "B", "AB", "O"), 3, "science", "medium"));

        // HARD (10 questions)
        allQuestions.add(new Question("What is the speed of light?",
                Arrays.asList("300,000 km/s", "150,000 km/s", "450,000 km/s", "600,000 km/s"), 0, "science", "hard"));
        allQuestions.add(new Question("What is the atomic number of Carbon?",
                Arrays.asList("4", "6", "8", "12"), 1, "science", "hard"));
        allQuestions.add(new Question("What is Avogadro's number?",
                Arrays.asList("6.02 x 10^22", "6.02 x 10^23", "6.02 x 10^24", "6.02 x 10^25"), 1, "science", "hard"));
        allQuestions.add(new Question("What is the half-life of Carbon-14?",
                Arrays.asList("573 years", "5,730 years", "57,300 years", "573,000 years"), 1, "science", "hard"));
        allQuestions.add(new Question("What particle has no electric charge?",
                Arrays.asList("Proton", "Electron", "Neutron", "Positron"), 2, "science", "hard"));
        allQuestions.add(new Question("What is the rarest naturally occurring element?",
                Arrays.asList("Gold", "Platinum", "Astatine", "Francium"), 2, "science", "hard"));
        allQuestions.add(new Question("What is the Heisenberg Uncertainty Principle about?",
                Arrays.asList("Energy", "Time", "Position and momentum", "Mass"), 2, "science", "hard"));
        allQuestions.add(new Question("How many amino acids are essential for humans?",
                Arrays.asList("7", "9", "11", "13"), 1, "science", "hard"));
        allQuestions.add(new Question("What is the pH of pure water?",
                Arrays.asList("5", "6", "7", "8"), 2, "science", "hard"));
        allQuestions.add(new Question("What is the closest star to Earth after the Sun?",
                Arrays.asList("Alpha Centauri", "Proxima Centauri", "Sirius", "Betelgeuse"), 1, "science", "hard"));

        // ========================================
        // HISTORY QUESTIONS
        // ========================================

        // EASY (10 questions)
        allQuestions.add(new Question("Who was the first President of the United States?",
                Arrays.asList("Jefferson", "Washington", "Adams", "Madison"), 1, "history", "easy"));
        allQuestions.add(new Question("In which country were the pyramids built?",
                Arrays.asList("Mexico", "Egypt", "Peru", "Iraq"), 1, "history", "easy"));
        allQuestions.add(new Question("What year did World War II begin?",
                Arrays.asList("1937", "1939", "1941", "1943"), 1, "history", "easy"));
        allQuestions.add(new Question("Who invented the telephone?",
                Arrays.asList("Edison", "Bell", "Tesla", "Marconi"), 1, "history", "easy"));
        allQuestions.add(new Question("What ship sank in 1912?",
                Arrays.asList("Lusitania", "Titanic", "Britannic", "Olympic"), 1, "history", "easy"));
        allQuestions.add(new Question("Which city was the first atomic bomb dropped on?",
                Arrays.asList("Tokyo", "Hiroshima", "Nagasaki", "Kyoto"), 1, "history", "easy"));
        allQuestions.add(new Question("Who was the British Prime Minister during WWII?",
                Arrays.asList("Chamberlain", "Churchill", "Attlee", "Eden"), 1, "history", "easy"));
        allQuestions.add(new Question("What ancient civilization built Machu Picchu?",
                Arrays.asList("Aztec", "Maya", "Inca", "Olmec"), 2, "history", "easy"));
        allQuestions.add(new Question("In which year did the Berlin Wall fall?",
                Arrays.asList("1987", "1989", "1991", "1993"), 1, "history", "easy"));
        allQuestions.add(new Question("Who was the ancient Egyptian queen?",
                Arrays.asList("Nefertiti", "Cleopatra", "Hatshepsut", "Ankhesenamun"), 1, "history", "easy"));

        // MEDIUM (10 questions)
        allQuestions.add(new Question("In which year did World War II end?",
                Arrays.asList("1943", "1944", "1945", "1946"), 2, "history", "medium"));
        allQuestions.add(new Question("Who discovered America in 1492?",
                Arrays.asList("Magellan", "Columbus", "Vespucci", "Cortez"), 1, "history", "medium"));
        allQuestions.add(new Question("What year did the French Revolution begin?",
                Arrays.asList("1776", "1789", "1799", "1804"), 1, "history", "medium"));
        allQuestions.add(new Question("Who was the first Roman Emperor?",
                Arrays.asList("Julius Caesar", "Augustus", "Nero", "Caligula"), 1, "history", "medium"));
        allQuestions.add(new Question("Which empire built the Colosseum?",
                Arrays.asList("Greek", "Roman", "Persian", "Ottoman"), 1, "history", "medium"));
        allQuestions.add(new Question("Who led the Confederate army in the US Civil War?",
                Arrays.asList("Grant", "Sherman", "Lee", "Jackson"), 2, "history", "medium"));
        allQuestions.add(new Question("What year did the Soviet Union collapse?",
                Arrays.asList("1989", "1990", "1991", "1992"), 2, "history", "medium"));
        allQuestions.add(new Question("Who was the first man to walk on the moon?",
                Arrays.asList("Buzz Aldrin", "Neil Armstrong", "John Glenn", "Alan Shepard"), 1, "history", "medium"));
        allQuestions.add(new Question("What dynasty built the Great Wall of China?",
                Arrays.asList("Han", "Tang", "Ming", "Qing"), 2, "history", "medium"));
        allQuestions.add(new Question("Who painted the Sistine Chapel ceiling?",
                Arrays.asList("Da Vinci", "Raphael", "Michelangelo", "Donatello"), 2, "history", "medium"));

        // HARD (10 questions)
        allQuestions.add(new Question("The Great Wall of China was built to protect against which group?",
                Arrays.asList("Mongols", "Japanese", "Romans", "Vikings"), 0, "history", "hard"));
        allQuestions.add(new Question("Which ancient wonder is still standing?",
                Arrays.asList("Colossus of Rhodes", "Hanging Gardens", "Great Pyramid", "Lighthouse"), 2, "history", "hard"));
        allQuestions.add(new Question("What year was the Magna Carta signed?",
                Arrays.asList("1115", "1215", "1315", "1415"), 1, "history", "hard"));
        allQuestions.add(new Question("Who was the last Tsar of Russia?",
                Arrays.asList("Alexander II", "Alexander III", "Nicholas I", "Nicholas II"), 3, "history", "hard"));
        allQuestions.add(new Question("What battle ended Napoleon's rule?",
                Arrays.asList("Austerlitz", "Waterloo", "Leipzig", "Borodino"), 1, "history", "hard"));
        allQuestions.add(new Question("Which Byzantine Emperor built the Hagia Sophia?",
                Arrays.asList("Constantine", "Justinian", "Theodosius", "Basil"), 1, "history", "hard"));
        allQuestions.add(new Question("What year was the Battle of Hastings?",
                Arrays.asList("1056", "1066", "1076", "1086"), 1, "history", "hard"));
        allQuestions.add(new Question("Who was the Mongol leader who conquered much of Asia?",
                Arrays.asList("Kublai Khan", "Genghis Khan", "Tamerlane", "Attila"), 1, "history", "hard"));
        allQuestions.add(new Question("What empire was ruled by Suleiman the Magnificent?",
                Arrays.asList("Persian", "Mughal", "Ottoman", "Byzantine"), 2, "history", "hard"));
        allQuestions.add(new Question("In which year did the Spanish Armada fail?",
                Arrays.asList("1568", "1578", "1588", "1598"), 2, "history", "hard"));

        // ========================================
        // TECHNOLOGY QUESTIONS
        // ========================================

        // EASY (10 questions)
        allQuestions.add(new Question("What does WWW stand for?",
                Arrays.asList("World Wide Web", "World Web Wide", "Web World Wide", "Wide World Web"), 0, "technology", "easy"));
        allQuestions.add(new Question("Which company makes the iPhone?",
                Arrays.asList("Samsung", "Google", "Apple", "Microsoft"), 2, "technology", "easy"));
        allQuestions.add(new Question("What does PC stand for?",
                Arrays.asList("Personal Computer", "Private Computer", "Public Computer", "Portable Computer"), 0, "technology", "easy"));
        allQuestions.add(new Question("What is the brain of a computer called?",
                Arrays.asList("RAM", "CPU", "GPU", "ROM"), 1, "technology", "easy"));
        allQuestions.add(new Question("Which social media platform uses a bird as its logo?",
                Arrays.asList("Facebook", "Instagram", "Twitter", "Snapchat"), 2, "technology", "easy"));
        allQuestions.add(new Question("What does USB stand for?",
                Arrays.asList("Universal Serial Bus", "United Serial Bus", "Universal System Bus", "United System Bus"), 0, "technology", "easy"));
        allQuestions.add(new Question("What is the name of Apple's voice assistant?",
                Arrays.asList("Alexa", "Cortana", "Siri", "Google"), 2, "technology", "easy"));
        allQuestions.add(new Question("Which key combination is used to copy text?",
                Arrays.asList("Ctrl+V", "Ctrl+C", "Ctrl+X", "Ctrl+Z"), 1, "technology", "easy"));
        allQuestions.add(new Question("What does Wi-Fi stand for?",
                Arrays.asList("Wireless Fidelity", "Wireless File", "Wide Fidelity", "Wired Fidelity"), 0, "technology", "easy"));
        allQuestions.add(new Question("Which company owns YouTube?",
                Arrays.asList("Facebook", "Google", "Microsoft", "Amazon"), 1, "technology", "easy"));

        // MEDIUM (10 questions)
        allQuestions.add(new Question("Who founded Microsoft?",
                Arrays.asList("Steve Jobs", "Bill Gates", "Mark Zuckerberg", "Elon Musk"), 1, "technology", "medium"));
        allQuestions.add(new Question("In what year was the first iPhone released?",
                Arrays.asList("2005", "2006", "2007", "2008"), 2, "technology", "medium"));
        allQuestions.add(new Question("What does RAM stand for?",
                Arrays.asList("Random Access Memory", "Read Access Memory", "Rapid Access Memory", "Real Access Memory"), 0, "technology", "medium"));
        allQuestions.add(new Question("Which programming language is known for web development?",
                Arrays.asList("Python", "JavaScript", "C++", "Swift"), 1, "technology", "medium"));
        allQuestions.add(new Question("What year was Facebook founded?",
                Arrays.asList("2002", "2004", "2006", "2008"), 1, "technology", "medium"));
        allQuestions.add(new Question("What does SSD stand for?",
                Arrays.asList("Solid State Drive", "Super Speed Drive", "Secure Storage Device", "System State Drive"), 0, "technology", "medium"));
        allQuestions.add(new Question("Which company developed the Android operating system?",
                Arrays.asList("Apple", "Microsoft", "Google", "Samsung"), 2, "technology", "medium"));
        allQuestions.add(new Question("What is the maximum number of characters in a tweet?",
                Arrays.asList("140", "200", "280", "320"), 2, "technology", "medium"));
        allQuestions.add(new Question("Which programming language is used for iOS apps?",
                Arrays.asList("Java", "Kotlin", "Swift", "C#"), 2, "technology", "medium"));
        allQuestions.add(new Question("What does VPN stand for?",
                Arrays.asList("Virtual Private Network", "Virtual Public Network", "Variable Private Network", "Verified Private Network"), 0, "technology", "medium"));

        // HARD (10 questions)
        allQuestions.add(new Question("What does CPU stand for?",
                Arrays.asList("Central Process Unit", "Central Processing Unit", "Computer Personal Unit", "Central Processor Unit"), 1, "technology", "hard"));
        allQuestions.add(new Question("Which company developed Android OS?",
                Arrays.asList("Apple", "Microsoft", "Google", "Samsung"), 2, "technology", "hard"));
        allQuestions.add(new Question("What is the binary representation of the number 10?",
                Arrays.asList("1001", "1010", "1011", "1100"), 1, "technology", "hard"));
        allQuestions.add(new Question("What does SQL stand for?",
                Arrays.asList("Structured Query Language", "Simple Query Language", "System Query Language", "Standard Query Language"), 0, "technology", "hard"));
        allQuestions.add(new Question("What is the time complexity of binary search?",
                Arrays.asList("O(n)", "O(log n)", "O(n²)", "O(1)"), 1, "technology", "hard"));
        allQuestions.add(new Question("Which protocol is used to send emails?",
                Arrays.asList("HTTP", "FTP", "SMTP", "TCP"), 2, "technology", "hard"));
        allQuestions.add(new Question("What is Moore's Law about?",
                Arrays.asList("Internet speed", "Transistor density", "Storage capacity", "Network bandwidth"), 1, "technology", "hard"));
        allQuestions.add(new Question("What does API stand for?",
                Arrays.asList("Application Programming Interface", "Advanced Programming Interface", "Application Process Interface", "Automated Programming Interface"), 0, "technology", "hard"));
        allQuestions.add(new Question("Which data structure uses LIFO?",
                Arrays.asList("Queue", "Stack", "Array", "Tree"), 1, "technology", "hard"));
        allQuestions.add(new Question("What is the default port for HTTPS?",
                Arrays.asList("80", "443", "8080", "3000"), 1, "technology", "hard"));

        // ========================================
        // SPORTS QUESTIONS
        // ========================================

        // EASY (10 questions)
        allQuestions.add(new Question("How many players are on a soccer team?",
                Arrays.asList("9", "10", "11", "12"), 2, "sports", "easy"));
        allQuestions.add(new Question("What sport uses a shuttlecock?",
                Arrays.asList("Tennis", "Badminton", "Volleyball", "Squash"), 1, "sports", "easy"));
        allQuestions.add(new Question("In which sport do you use a racket and a ball?",
                Arrays.asList("Soccer", "Tennis", "Basketball", "Baseball"), 1, "sports", "easy"));
        allQuestions.add(new Question("How many points is a touchdown in American football?",
                Arrays.asList("3", "6", "7", "10"), 1, "sports", "easy"));
        allQuestions.add(new Question("What sport is played at Wimbledon?",
                Arrays.asList("Cricket", "Tennis", "Soccer", "Golf"), 1, "sports", "easy"));
        allQuestions.add(new Question("How many players are on a basketball team on the court?",
                Arrays.asList("4", "5", "6", "7"), 1, "sports", "easy"));
        allQuestions.add(new Question("What color card does a referee show for ejection?",
                Arrays.asList("Yellow", "Red", "Blue", "Green"), 1, "sports", "easy"));
        allQuestions.add(new Question("In which sport do you hit a puck?",
                Arrays.asList("Baseball", "Cricket", "Hockey", "Golf"), 2, "sports", "easy"));
        allQuestions.add(new Question("How many bases are there in baseball?",
                Arrays.asList("2", "3", "4", "5"), 2, "sports", "easy"));
        allQuestions.add(new Question("What sport is played in the NBA?",
                Arrays.asList("Baseball", "Football", "Basketball", "Hockey"), 2, "sports", "easy"));

        // MEDIUM (10 questions)
        allQuestions.add(new Question("In which sport is the term 'love' used?",
                Arrays.asList("Tennis", "Golf", "Cricket", "Baseball"), 0, "sports", "medium"));
        allQuestions.add(new Question("How many rings are on the Olympic flag?",
                Arrays.asList("4", "5", "6", "7"), 1, "sports", "medium"));
        allQuestions.add(new Question("What is the maximum score in 10-pin bowling?",
                Arrays.asList("200", "250", "300", "350"), 2, "sports", "medium"));
        allQuestions.add(new Question("In soccer, what is it called when a player scores 3 goals?",
                Arrays.asList("Double", "Hat-trick", "Triple", "Grand Slam"), 1, "sports", "medium"));
        allQuestions.add(new Question("How long is a marathon in miles?",
                Arrays.asList("20.2", "23.1", "26.2", "30.1"), 2, "sports", "medium"));
        allQuestions.add(new Question("What is the national sport of Canada?",
                Arrays.asList("Ice Hockey", "Lacrosse", "Basketball", "Baseball"), 1, "sports", "medium"));
        allQuestions.add(new Question("In golf, what is one stroke under par called?",
                Arrays.asList("Eagle", "Birdie", "Bogey", "Albatross"), 1, "sports", "medium"));
        allQuestions.add(new Question("How many Grand Slam tennis tournaments are there?",
                Arrays.asList("2", "3", "4", "5"), 2, "sports", "medium"));
        allQuestions.add(new Question("What is the Super Bowl trophy called?",
                Arrays.asList("Stanley Cup", "Vince Lombardi Trophy", "World Series", "Larry O'Brien Trophy"), 1, "sports", "medium"));
        allQuestions.add(new Question("In cricket, how many players are on each team?",
                Arrays.asList("9", "10", "11", "12"), 2, "sports", "medium"));

        // HARD (10 questions)
        allQuestions.add(new Question("What is the diameter of a basketball hoop in inches?",
                Arrays.asList("16", "18", "20", "22"), 1, "sports", "hard"));
        allQuestions.add(new Question("Which country won the first FIFA World Cup?",
                Arrays.asList("Brazil", "Argentina", "Uruguay", "Germany"), 2, "sports", "hard"));
        allQuestions.add(new Question("Who holds the record for most Olympic gold medals?",
                Arrays.asList("Usain Bolt", "Michael Phelps", "Carl Lewis", "Mark Spitz"), 1, "sports", "hard"));
        allQuestions.add(new Question("What is the only sport to be played on the moon?",
                Arrays.asList("Baseball", "Golf", "Soccer", "Frisbee"), 1, "sports", "hard"));
        // HARD (10 questions)
        allQuestions.add(new Question("What is the diameter of a basketball hoop in inches?",
                Arrays.asList("16", "18", "20", "22"), 1, "sports", "hard"));
        allQuestions.add(new Question("Which country won the first FIFA World Cup?",
                Arrays.asList("Brazil", "Argentina", "Uruguay", "Germany"), 2, "sports", "hard"));
        allQuestions.add(new Question("Who holds the record for most Olympic gold medals?",
                Arrays.asList("Usain Bolt", "Michael Phelps", "Carl Lewis", "Mark Spitz"), 1, "sports", "hard"));
        allQuestions.add(new Question("What is the only sport to be played on the moon?",
                Arrays.asList("Baseball", "Golf", "Soccer", "Frisbee"), 1, "sports", "hard"));
        allQuestions.add(new Question("How long is an Olympic swimming pool in meters?",
                Arrays.asList("25", "50", "75", "100"), 1, "sports", "hard"));
        allQuestions.add(new Question("Which boxer was known as 'The Greatest'?",
                Arrays.asList("Mike Tyson", "Muhammad Ali", "Floyd Mayweather", "Joe Frazier"), 1, "sports", "hard"));
        allQuestions.add(new Question("What country invented table tennis?",
                Arrays.asList("China", "Japan", "England", "Germany"), 2, "sports", "hard"));
        allQuestions.add(new Question("Which tennis tournament is played on clay courts?",
                Arrays.asList("Wimbledon", "US Open", "Australian Open", "French Open"), 3, "sports", "hard"));
        allQuestions.add(new Question("How many minutes are played in a rugby union match?",
                Arrays.asList("60", "70", "80", "90"), 2, "sports", "hard"));
        allQuestions.add(new Question("Which Formula 1 driver has the most world championships?",
                Arrays.asList("Ayrton Senna", "Sebastian Vettel", "Lewis Hamilton", "Michael Schumacher"), 3, "sports", "hard"));
    }

    // ========================================
    // HELPER METHODS
    // ========================================

    // Get all questions
    public List<Question> getAllQuestions() {
        return allQuestions;
    }

    // Get questions by category and difficulty
    public List<Question> getQuestionsByCategory(String category, String difficulty) {
        List<Question> filtered = new ArrayList<>();

        for (Question q : allQuestions) {
            if (q.getCategory().equals(category) &&
                    q.getDifficulty().equals(difficulty)) {
                filtered.add(q);
            }
        }

        Collections.shuffle(filtered);
        return filtered;
    }
}