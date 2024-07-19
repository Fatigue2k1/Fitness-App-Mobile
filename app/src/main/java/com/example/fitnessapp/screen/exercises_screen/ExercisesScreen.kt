package com.example.fitnessapp.screen.exercises_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

data class Exercise(
    val name: String,
    val guideline: String,
    val description: String,
    val targetMuscle: String,
    val type: String
)

val exercises = listOf(
    Exercise(
        name = "Bar Dips",
        guideline = "Grip a dip station about shoulder-width apart, and climb or jump to get into the starting position. " +
                "Lower yourself with control until your shoulder is below your elbow, or as deep as you comfortably can. " +
                "Reverse the motion and return to the starting position.",
        description = "The bar dip is a classic upper-body exercise, and for most people, heavy enough to give a good training effect with the body weight alone as resistance.",
        targetMuscle = "Primary muscles worked:\nChest\nFront Deltoid\nSecondary muscles worked:\nTriceps",
        type = "Calisthenics"
    ),
    Exercise(
        name = "Barbell Curl",
        guideline = "Grip a bar with an underhand (supinated) grip, hands about shoulder-width apart.\n" +
                "Lift the bar with control, by flexing your elbows.\n" +
                "Don’t let your upper arm travel back during the curl, keep it at your side or move it slightly forward.\n" +
                "Reverse the movement and lower the bar back to the starting position.",
        description = "The barbell curl is one of the most classic (and best) " +
                "exercises you can do for your biceps.",
        targetMuscle = "Primary muscles worked:\n" +
                "Biceps\n" +
                "Secondary muscles worked:\n" +
                "Forearm Flexors",
        type = "GYM"
    ),
    Exercise(
        name = "Barbell Standing Triceps Extension",
        guideline = "Grip a barbell with a close grip, and lift it up to straight arms over your head.\n" +
                "Lower the barbell behind your head while keeping your upper arms still and vertical.\n" +
                "Reverse the motion and extend your arms again.",
        description = "Standing triceps extension is an effective exercise for " +
                "isolating the tricep muscle. The standing position puts a great " +
                "stretch on the triceps and likely emphasizes the long head of the " +
                "triceps more because of the elevated position of the upper arm.",
        targetMuscle = "Primary muscles worked:\n" +
                "Triceps",
        type = "GYM"
    ),
    Exercise(
        name = "Barbell Rows",
        guideline = "Grip the bar with an overhand grip.\n" +
                "Lean forward with the bar hanging from straight arms.\n" +
                "Inhale and pull the bar towards you.\n" +
                "Pull the bar as high as you can so that it touches your abs or chest, if possible.\n" +
                "With control, lower the bar back to the starting position.",
        description = "The barbell row is a classic back exercise and one of the most " +
                "popular strength training exercises.\n" +
                "\n" +
                "The barbell row is also known as the bent-over row, which refers to " +
                "the bent-over position you hold throughout the exercise.",
        targetMuscle = "Primary muscles worked:\n" +
                "Lats\n" +
                "Trapezius\n" +
                "Rear Deltoids\n" +
                "Secondary muscles worked:\n" +
                "Biceps\n" +
                "Lower Back\n" +
                "Forearm Flexors\n" +
                "Rotator Cuff",
        type = "GYM"
    ),
    Exercise(
        name = "Bar Muscle-Up",
        guideline = "Grip the bar with a false grip (your wrists flexed and your knuckles above the bar).\n" +
                "Initiate the movement by powerfully pulling the bar toward your stomach.\n" +
                "When you reach the transition point between pulling and pushing, lean forward so the bar connects with your stomach.\n" +
                "Finish the movement by extending your elbows t",
        description = "The bar muscle-up is a challenging and impressive gymnastic " +
                "move that requires a mixed skill base of strength, technique, and " +
                "coordination.",
        targetMuscle = "Primary Muscles Worked\n" +
                "Chest\n" +
                "Lats\n" +
                "Triceps\n" +
                "Secondary Muscles Worked\n" +
                "Biceps\n" +
                "Forearm Flexors\n" +
                "Front Deltoid\n" +
                "Rear Deltoids\n" +
                "Rotator Cuffs",
        type = "Calisthenics"
    ),
    Exercise(
        name = "Bench Press",
        guideline = "Lie on the bench, pull your shoulder blades together and down, and slightly arch your back. " +
                "Grip the bar slightly wider than shoulder-width apart. " +
                "Inhale, hold your breath, and unrack the bar. " +
                "Lower the bar with control, until it touches your chest somewhere close to your sternum. " +
                "Push the bar up to the starting position while exhaling. " +
                "Take another breath while in the top position, and repeat for reps.",
        description = "The barbell bench press is one of the most popular exercises in the world and one of the best chest exercises you can do.",
        targetMuscle = "Primary muscles worked:\nChest\nFront Deltoid\nSecondary muscles worked:\nTriceps",
        type = "GYM"
    ),
    Exercise(
        name = "Chin-Up",
        guideline = "Grip the bar with a supinated grip (palms facing you), about shoulder-width apart.\n" +
                "Keep your chest up, and look up at the bar.\n" +
                "Inhale and pull yourself up until your chin is over the bar, or the bar touches your upper chest.\n" +
                "Exhale and lower yourself with control until your arms are fully extended.",
        description = "Chin-ups train both your lats and arm flexors effectively. " +
                "With a supinated grip like in this exercise, you make it possible " +
                "to focus even more on your arm flexors, if you want to.",
        targetMuscle = "Primary muscles worked:\n" +
                "Lats\n" +
                "Secondary muscles worked:\n" +
                "Biceps\n" +
                "Rear Deltoids\n" +
                "Forearm Flexors\n" +
                "Rotator Cuffs",
        type = "Calisthenics"
    ),
    Exercise(
        name = "Crunches",
        guideline = "Lie on your back, with your hands in front of your chest and your knees bent to about 90 degrees.\n" +
                "Lift your upper body by contracting your abs and bending forward.\n" +
                "Bend as far forward as possible while still keeping your low back " +
                "in contact with the floor, and then return to the starting position.",
        description = "The crunch is a classic body weight exercise for " +
                "strengthening your abs. The exercise can be varied by lying on a " +
                "soft training ball (i.e. pilates/bosu ball), and you can increase " +
                "the load by holding a weight against your chest.",
        targetMuscle = "Primary muscles worked:\n" +
                "Abs\n" +
                "Secondary muscles worked:\n" +
                "Obliques\n",
        type = "Calisthenics"
    ),
    Exercise(
        name = "Dumbbell Shoulder Press",
        guideline = "Grab a pair of dumbbells, and lift them up to the starting position at your shoulders.\n" +
                "Inhale and lightly brace your core.\n" +
                "Press the dumbbells up to straight arms, while exhaling.\n" +
                "Inhale at the top, or while lowering the dumbbells with control back to your shoulders.\n" +
                "Repeat for reps.",
        description = "The dumbbell shoulder press is a variant of the barbell " +
                "overhead press. The dumbbells increase the demand for shoulder " +
                "stability, and can also enable a longer range of motion. With dumbbells, " +
                "you might also be able to move your arms more to your sides compared " +
                "to a barbell, which can emphasize the training effect of the medial deltoids.",
        targetMuscle = "Primary muscles worked:\n" +
                "Front Deltoid\n" +
                "Secondary muscles worked:\n" +
                "Triceps\n" +
                "Lateral Deltoid",
        type = "GYM"
    ),
    Exercise(
        name = "Dumbbell Lateral Raise",
        guideline = "Hold a pair of dumbbells in almost straight arms hanging by your sides.\n" +
                "With control, lift the dumbbells out to your sides until your upper arms are horizontal.\n" +
                "Lower the dumbbells with control.\n" +
                "Repeat for reps.",
        description = "The dumbbell lateral raise is an isolation exercise for " +
                "primarily the lateral deltoid muscles. This exercise is popular for " +
                "its ability to sculpt and strengthen the shoulder muscles and is easy " +
                "to perform since the only equipment you need is a pair of dumbbells.",
        targetMuscle = "Primary muscles worked:\n" +
                "Lateral Deltoid\n" +
                "Secondary muscles worked:\n" +
                "Front Deltoid",
        type = "GYM"
    ),
    Exercise(
        name = "Hanging Leg Raise",
        guideline = "Jump up and grab a bar, placed high enough that you can hang from it with straight legs.\n" +
                "Without swinging, lift your legs as high as you can in front of you.\n" +
                "Lower your legs again, with control.",
        description = "Hanging leg raises train your abs and your hip flexors. " +
                "The exercise can be made easier by bending your knees, called " +
                "hanging knee raises, and you can make it heavier by using ankle weights " +
                "or by holding a small dumbbell between your feet.",
        targetMuscle = "Primary muscles worked:\n" +
                "Abs\n" +
                "Secondary muscles worked:\n" +
                "Obliques",
        type = "Calisthenics"
    ),
    Exercise(
        name = "Incline Bench Press",
        guideline = "Sitting on an inclined bench, unrack a barbell and hold it on straight arms above your shoulders.\n" +
                "Inhale and lower the bar down to your chest.\n" +
                "Press the bar up to straight arms while exhaling.",
        description = "The incline bench press is a mixture of the regular bench press " +
            "and the overhead press, and both the front deltoids and the upper portions " +
            "of the chest muscles are trained in this exercise. " +
            "Thanks to the bench’s inclination, many experience this exercise as easy " +
            "on their shoulders and that they can get a nice, long range of motion.",
        targetMuscle = "Primary muscles worked:\n" +
                "Chest\n" +
                "Front Deltoid\n" +
                "Secondary muscles worked:\n" +
                "Triceps",
        type = "GYM"
    ),
    Exercise(
        name = "Incline Dumbbell Press",
        guideline = "Adjust the incline of a bench to be around 30-45 degrees.\n" +
                "Sit down and lift a pair of dumbbells to the starting position.\n" +
                "Press the dumbbells up to straight arms while exhaling.\n" +
                "Inhale at the top or while lowering the dumbbells with control back to your shoulders.",
        description = "The incline dumbbell press is a mixture of the dumbbell chest press " +
                "and the shoulder press, and both the front deltoids and the upper portions " +
                "of the chest muscles are worked in this exercise. Thanks to the inclination " +
                "of the bench, many experience this exercise as easy on their shoulders " +
                "and that they can get a nice, long range of motion.",
        targetMuscle = "Primary muscles worked:\n" +
                "Chest\n" +
                "Front Deltoid\n" +
                "Secondary muscles worked:\n" +
                "Triceps",
        type = "GYM"
    ),
    Exercise(
        name = "Incline Dumbbell Curl",
        guideline = "Grab a pair of dumbbells, and sit down on an inclined bench. Let your arms hang straight down by your sides.\n" +
                "Lift the dumbbells with control, by flexing your elbows.\n" +
                "Reverse the movement and lower the dumbbells back to the starting position.",
        description = "Incline dumbbell curl is an exercise where biceps brachii " +
                "works at a slightly longer muscle lengths than in regular bicep curls. " +
                "This is because the biceps originates from the shoulder blade, and is " +
                "thus extended when your arm is behind your body, like in this exercise. " +
                "This position can potentially lead to greater muscle growth than " +
                "regular curls. Another consequence is that it is slightly harder " +
                "to cheat with your technique in this exercise.",
        targetMuscle = "Primary muscles worked:\n" +
                "Biceps\n" +
                "Secondary muscles worked:\n" +
                "Forearm Flexors",
        type = "GYM"
    ),
    Exercise(
        name = "Incline Push-Up",
        guideline = "Assume the starting position, with hands slightly wider than shoulder width apart.\n" +
                "Try to form a straight line from head to feet, and brace your abdomen slightly.\n" +
                "Lower yourself as deep as you can, while inhaling.\n" +
                "Reverse the motion when you’ve touched the elevation, and push yourself up to straight arms again while exhaling.\n" +
                "Repeat for reps.",
        description = "The push-up is a classic bodyweight exercise for the upper body, " +
                "with the benefit that you don’t need any equipment to perform it. " +
                "In push-ups, you lift about 70% of your own body weight, but you can " +
                "increase or decrease that amount by placing your feet or hands, " +
                "respectively, on an elevation. This version of the push-up decreases " +
                "the load since your hands are elevated to the sofa, chair, bench or " +
                "whatever you are using.",
        targetMuscle = "Primary muscles worked:\n" +
                "Chest\n" +
                "Front Deltoid\n" +
                "Secondary muscles worked:\n" +
                "Triceps\n" +
                "Abs",
        type = "Calisthenics"
    ),
    Exercise(
        name = "Kneeling Ab Wheel Roll-Out",
        guideline = "Sit with your knees on a soft pad, and place the ab wheel on the floor in front of you.\n" +
                "Roll out as far as you can, and maintain a straight back throughout the movement.\n" +
                "Reverse the movement with control, and return to the starting position. ",
        description = "This exercise can be performed either with an ab wheel or " +
                "with a barbell that has good rotation. The exercise is easier when " +
                "done on your knees, and harder when on your feet.",
        targetMuscle = "Primary muscles worked:\n" +
                "Abs\n" +
                "Secondary muscles worked:\n" +
                "Obliques",
        type = "Calisthenics"
    ),
    Exercise(
        name = "Lying Leg Raise",
        guideline = "Lie down with your back on the floor, and your arms at your sides.\n" +
                "With straight legs, lift your legs until they are pointing straight up.\n" +
                "Lower your legs again, with control.",
        description = "Lying leg raises train your abs and your hip flexors. " +
                " exercise can be made easier by bending your knees slightly, and you " +
                "can make it heavier by using ankle weights.",
        targetMuscle = "Primary muscles worked:\n" +
                "Abs\n" +
                "Secondary muscles worked:\n" +
                "Obliques",
        type = "Calisthenics"
    ),
    Exercise(
        name = "Leg Extension",
        guideline = "Adjust the machine so that you are correctly positioned. Your knees should be in line with the machine’s joint.\n" +
                "Extend your knees with control, until they are completely straight.\n" +
                "Slowly lower the weight again.",
        description = "The seated leg curl is an isolation exercise for the quadriceps " +
                "and is one of the most popular leg exercises. \n Whether your goal is to " +
                "get big quads or to get stronger legs, the leg extension is a great " +
                "exercise to incorporate into your workout routine.",
        targetMuscle = "Primary muscles worked:\n" +
                "Quads",
        type = "GYM"
    ),
    Exercise(
        name = "Leg Press",
        guideline = "Adjust the machine so that you only need to extend your legs slightly to be able to release the weights. Adjust the safety pins so that they catch the weight if you are unable to lift it.\n" +
                "Place your feet on the platform, about shoulder-width apart.\n" +
                "Inhale and lower the weight by bending your legs.\n" +
                "Lower the weight as deep as possible without rounding your back and while keeping your glutes on the seat.\n" +
                "Press the weight back up again as you exhale.",
        description = "The leg press is a popular leg exercise that, compared with " +
                "barbell squats, requires less balance, control, and mobility. " +
                "This means that this exercise can be easier to start with for a " +
                "beginner and also that you can train closer to muscular failure " +
                "without thinking about balance.",
        targetMuscle = "Primary muscles worked:\n" +
                "Quads\n" +
                "Glutes\n" +
                "Adductors\n" +
                "Secondary muscles worked:\n" +
                "Hamstrings",
        type = "GYM"
    ),
    Exercise(
        name = "Machine Chest Fly",
        guideline = "Adjust the back support and handles so that you can grip the handles at shoulder height and get a long range of motion.\n" +
                "With just a slight bend in the arms, push the handles forward until they meet in front of your body.\n" +
                "With control, let the handles go back to the starting position.",
        description = "The resistance curve in machine chest flyes is more evenly distributed " +
                "than the dumbbell chest flyes, and you’ll have more of a constant load on " +
                "your chest muscles throughout the whole range of motion. " +
                "It’s easy to learn and suitable for lifters at all levels.",
        targetMuscle = "Primary muscles worked:\n" +
                "Chest\n" +
                "Secondary muscles worked:\n" +
                "Front Deltoid",
        type = "GYM"
    ),
    Exercise(
        name = "Overhead Press",
        guideline = "First, place a barbell in a rack at about chest height.\n" +
                "Grip the bar slightly wider than shoulder-width apart, and step close to it.\n" +
                "Inhale, lightly brace your core, and unrack the bar.\n" +
                "Let the bar rest against your front delts while you step back from the rack.\n" +
                "Press the bar up to straight arms while exhaling.\n" +
                "Inhale at the top or while lowering the bar with control back to your shoulders.\n" +
                "Repeat for reps.",
        description = "The barbell overhead press (also known as the shoulder press) " +
                "is a classic strength training exercise that mainly targets your " +
                "shoulders and triceps.",
        targetMuscle = "Primary muscles worked:\n" +
                "Front Deltoid\n" +
                "Secondary muscles worked:\n" +
                "Triceps\n" +
                "Lateral Deltoid",
        type = "GYM"
    ),
    Exercise(
        name = "Plank",
        guideline = "Stand on your elbows and feet.\n" +
                "Brace your abs and try to form and hold a straight line from your " +
                "head to feet.\n",
        description = "The plank is a bodyweight exercise to strengthen your " +
                "abdominals. The exercise can be made more challenging by placing " +
                "your hands or feet on an unstable surface (such as a Bosu ball, " +
                "balance board, or in suspended rings), or by placing a weight on " +
                "your back.",
        targetMuscle = "Primary muscles worked:\n" +
                "Abs\n" +
                "Secondary muscles worked:\n" +
                "Obliques",
        type = "Calisthenics"
    ),
    Exercise(
        name = "Pull-Ups",
        guideline = "Grip the bar with palms facing away from you, slightly wider than shoulder-width.\n" +
                "Keep your chest up, and look up at the bar.\n" +
                "Inhale and pull yourself up until your chin is over the bar or the bar touches your upper chest.\n" +
                "Exhale and lower yourself with control until your arms are fully extended.",
        description = "Pull-ups train both your lats and arm flexors effectively. " +
                "By using a grip slightly wider than shoulder-width, you get a good " +
                "range of motion and high muscle activation in all the working muscles.",
        targetMuscle = "Primary muscles worked:\n" +
                "Lats\n" +
                "Secondary muscles worked:\n" +
                "Biceps\n" +
                "Rear Deltoids\n" +
                "Forearm Flexors\n" +
                "Rotator Cuffs",
        type = "Calisthenics"
    ),
    Exercise(
        name = "Reverse Machine Fly",
        guideline = "Adjust the chest support and handles so that you can grip the handles at shoulder height and get a long range of motion.\n" +
                "With just a slight bend in the arms, pull the handles backward by bringing your arms out to the sides.\n" +
                "Reverse the movement and let the handles go back to the starting position.",
        description = "Reverse machine flyes is an isolation exercise for the muscles " +
                "that horisontally extend your upper arms, and thus trains many of the " +
                "antagonists of common pressing exercises.",
        targetMuscle = "Primary muscles worked:\n" +
                "Rear Deltoid\n" +
                "Rotator Cuff\n" +
                "Secondary muscles worked:\n" +
                "Trapezius",
        type = "GYM"
    ),
    Exercise(
        name = "Romanian Deadlift",
        guideline = "Get into the starting position by deadlifting a barbell off the floor, or by unracking it from a barbell rack.\n" +
                "Inhale, brace your core slightly, and lean forward by hinging in your hips. Keep your knees almost completely extended.\n" +
                "Lean forward as far as possible without rounding your back. You don’t have to touch the barbell to the floor, although it is OK if you do.\n" +
                "Reverse the movement and return to the starting position. Exhale on the way up.\n" +
                "Take another breath, and repeat for reps.",
        description = "The Romanian deadlift (or RDL) is a classic barbell " +
                "exercise for strengthening your posterior chain muscles, such as your" +
                " glutes, hamstrings, and lower back.\n" +
                "\n" +
                "It is usually performed with a barbell, but dumbbells and " +
                "kettlebells are common variations.",
        targetMuscle = "Primary muscles worked:\n" +
                "Glutes\n" +
                "Lower Back\n" +
                "Hamstrings\n" +
                "Secondary muscles worked:\n" +
                "Adductors\n" +
                "Trapezius\n" +
                "Forearm Flexors",
        type = "GYM"
    ),
    Exercise(
        name = "Squat",
        guideline = "Place the bar on your upper back. Inhale and brace your core slightly, and unrack the bar.\n" +
                "Take two steps back, and adjust your foot position.\n" +
                "Squat as deep as possible with good technique.\n" +
                "With control, stop and reverse the movement, extending your hips and legs again.\n" +
                "Exhale on the way up or exchange air in the top position.\n" +
                "Inhale and repeat for reps.",
        description = "The squat is known as “the king of all exercises” – " +
                "and for good reason. Squats have been the staple for bodybuilders " +
                "looking to grow their leg muscles for decades, and in strength and " +
                "conditioning, they have been used (and still are used) to improve " +
                "almost every athletic endeavor that is undertaken on two feet.",
        targetMuscle = "Primary muscles worked:\n" +
                "Quads\n" +
                "Adductors\n" +
                "Glutes\n" +
                "Lower Back\n" +
                "Secondary muscles worked:\n" +
                "Calves",
        type = "GYM"
    ),
    Exercise(
        name = "Tricep Pushdown With Bar",
        guideline = "Stand one step away from the cable pulley, and grip a bar about shoulder-width apart.\n" +
                "Pull the handle down until your upper arms are perpendicular to the floor. This is the starting position.\n" +
                "Push the handle down until your arms are fully extended.\n" +
                "With control, let the handle up again.",
        description = "The tricep pushdown is an isolation triceps exercise, " +
                "often performed with a bar or a rope.",
        targetMuscle = "Primary muscles worked:\n" +
                "Triceps",
        type = "GYM"
    ),
    Exercise(
        name = "Tricep Pushdown With Rope",
        guideline = "Fasten a rope handle in the upper position of a cable pulley. Grip the rope with an overhand grip, and take one step back from the pulley.\n" +
                "Pull the rope down until your upper arms are perpendicular to the floor. This is the starting position.\n" +
                "Push the rope down until your arms are fully extended.\n" +
                "With control, let the rope up again.",
        description = "Tricep pushdown is an isolation exercise for your triceps " +
                "where it is easy to focus on the muscles working.",
        targetMuscle = "Primary muscles worked:\n" +
                "Triceps",
        type = "GYM"
    ),
    Exercise(
        name = "",
        guideline = "",
        description = "",
        targetMuscle = "",
        type = ""
    ),
)

@Composable
fun ExercisesScreen(navController: NavHostController) {
    var searchQuery by remember { mutableStateOf("") }
    val filteredExercises = remember(searchQuery) {
        exercises.filter {
            it.name.contains(searchQuery, ignoreCase = true)
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        TextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Search Exercises") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(filteredExercises) { exercise ->
                ExerciseItem(exercise)
            }
        }
    }
}

@Composable
fun ExerciseItem(exercise: Exercise) {
    var showDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { showDialog = true },
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = exercise.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(exercise.name) },
            text = {
                Column {
                    Text(text = "Guideline:", fontWeight = FontWeight.Bold)
                    Text(text = exercise.guideline)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Description:", fontWeight = FontWeight.Bold)
                    Text(text = exercise.description)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Target Muscle:", fontWeight = FontWeight.Bold)
                    Text(text = exercise.targetMuscle)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Type:", fontWeight = FontWeight.Bold)
                    Text(text = exercise.type)
                }
            },
            confirmButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Close")
                }
            },
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )
    }
}
