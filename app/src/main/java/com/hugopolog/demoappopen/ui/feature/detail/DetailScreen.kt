package com.hugopolog.demoappopen.ui.feature.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.hugopolog.domain.entities.repository.RepositoryModel

@Composable
fun DetailScreen(ownerId: String, repositoryId: String) {
    val viewModel = hiltViewModel<DetailViewModel>()

    LaunchedEffect(Unit) {
        viewModel.onAction(DetailActions.LoadRepository(ownerId, repositoryId))
    }

    DetailScreenContent(
        state = viewModel.screenState,
        onAction = viewModel::onAction
    )
}

@Composable
fun DetailScreenContent(
    state: DetailState,
    onAction: (DetailActions) -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        when {

            state.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            state.error != null -> {

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = state.error,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = { onAction(DetailActions.RetryLoad) }
                    ) {
                        Text("Retry")
                    }
                }
            }

            state.repository != null -> {
                RepositoryDetail(state.repository)
            }
        }
    }
}

@Composable
fun RepositoryDetail(repo: RepositoryModel) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                model = repo.owner.avatar_url,
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {

                Text(
                    text = repo.name,
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    text = repo.owner.login,
                    color = Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        repo.description?.let {

            Text(
                text = it,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        StatsRow(repo)

        Spacer(modifier = Modifier.height(16.dp))

        repo.language?.let {

            Text(
                text = "Language: $it",
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        TopicsSection(repo)

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { /* abrir navegador */ }
        ) {
            Text("Open in GitHub")
        }
    }
}

@Composable
fun StatsRow(repo: RepositoryModel) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        StatItem("⭐", repo.stargazers_count.toString())
        StatItem("🍴", repo.forks_count.toString())
        StatItem("🐞", repo.open_issues_count.toString())
    }
}

@Composable
fun StatItem(icon: String, value: String) {

    Row (
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = icon,
            color = Color.White
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = value,
            color = Color.White
        )
    }
}

@Composable
fun TopicsSection(repo: RepositoryModel) {

    repo.topics?.let { topics ->

        if (topics.isNotEmpty()) {

            LazyRow {

                items(topics.size) { index ->

                    Surface(
                        color = Color.DarkGray,
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .padding(end = 8.dp)
                    ) {

                        Text(
                            text = topics[index],
                            color = Color.White,
                            modifier = Modifier.padding(
                                horizontal = 12.dp,
                                vertical = 6.dp
                            )
                        )
                    }
                }
            }
        }
    }
}