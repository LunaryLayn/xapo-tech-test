package com.hugopolog.demoappopen.ui.feature.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.icons.Icons
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.hugopolog.domain.entities.repository.RepositoryModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.hugopolog.demoappopen.R
import com.hugopolog.domain.entities.config.query.OrderType

@Composable
fun RepositoryItem(repository: RepositoryModel, onRepositoryClicked : () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxWidth().clickable {
                onRepositoryClicked()
            }
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .padding(16.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                model = repository.owner.avatar_url,
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = repository.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }

        repository.description?.let {
            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = it,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            repository.language?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.width(16.dp))
            }

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = Color(0xFFFFC107),
                modifier = Modifier.size(16.dp)
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = repository.stargazers_count.toString(),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}


@Composable
fun SearchAndSortBar(
    query: String,
    sort: OrderType,
    onQueryChange: (String) -> Unit,
    onSortChange: (OrderType) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {

        OutlinedTextField(
            value = query,
            onValueChange = onQueryChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(stringResource(com.hugopolog.demoappopen.R.string.search)) },
            singleLine = true,
            shape = RoundedCornerShape(16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row {

            Button(
                onClick = { onSortChange(OrderType.ASC) },
                colors = ButtonDefaults.buttonColors(
                    containerColor =
                        if (sort == OrderType.ASC) Color.Gray else Color.DarkGray
                )
            ) {
                Text("ASC")
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = { onSortChange(OrderType.DESC) },
                colors = ButtonDefaults.buttonColors(
                    containerColor =
                        if (sort == OrderType.DESC) Color.Gray else Color.DarkGray
                )
            ) {
                Text("DESC")
            }
        }
    }
}

@Composable
fun LottieLoader() {

    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.github_loader)
    )

    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )

    LottieAnimation(
        composition = composition,
        progress = { progress }
    )
}