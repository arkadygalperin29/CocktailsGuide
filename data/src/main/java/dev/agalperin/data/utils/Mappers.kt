package dev.agalperin.data.utils

import dev.agalperin.data.models.Cocktail
import dev.agalperin.data.models.Ingredient
import dev.agalperin.data.models.IngredientDetailed
import dev.agalperin.database.models.CocktailDBO
import dev.agalperin.database.models.IngredientDBO
import dev.agalperin.database.models.IngredientDetailedDBO
import dev.agalperin.network.models.CocktailDTO
import dev.agalperin.network.models.IngredientDTO
import dev.agalperin.network.models.IngredientDetailedDTO

internal fun CocktailDBO.toCocktail(): Cocktail {
    return Cocktail(
        id = id,
        name = name,
        category = category,
        alcoholic = alcoholic,
        videoLink = videoLink,
        tags = tags,
        glass = glass,
        instructions = instructions,
        drinkImage = drinkImage,
        strIngredient1 = strIngredient1,
        strIngredient2 = strIngredient2,
        strIngredient3 = strIngredient3,
        strIngredient4 = strIngredient4,
        strIngredient5 = strIngredient5,
        strIngredient6 = strIngredient6,
        strIngredient7 = strIngredient7,
        strIngredient8 = strIngredient8,
        strIngredient9 = strIngredient9,
        strIngredient10 = strIngredient10,
        strIngredient11 = strIngredient11,
        strIngredient12 = strIngredient12,
        strIngredient13 = strIngredient13,
        strIngredient14 = strIngredient14,
        strIngredient15 = strIngredient15,
        strMeasure1 = strMeasure1,
        strMeasure2 = strMeasure2,
        strMeasure3 = strMeasure3,
        strMeasure4 = strMeasure4,
        strMeasure5 = strMeasure5,
        strMeasure6 = strMeasure6,
        strMeasure7 = strMeasure7,
        strMeasure8 = strMeasure8,
        strMeasure9 = strMeasure9,
        strMeasure10 = strMeasure10,
        strMeasure11 = strMeasure11,
        strMeasure12 = strMeasure12,
        strMeasure13 = strMeasure13,
        strMeasure14 = strMeasure14,
        strMeasure15 = strMeasure15,
    )
}

internal fun CocktailDTO.toCocktail(): Cocktail {
    return Cocktail(
        id = id,
        name = name,
        category = category,
        alcoholic = alcoholic,
        videoLink = videoLink,
        tags = tags,
        glass = glass,
        instructions = instructions,
        drinkImage = drinkImage,
        strIngredient1 = strIngredient1,
        strIngredient2 = strIngredient2,
        strIngredient3 = strIngredient3,
        strIngredient4 = strIngredient4,
        strIngredient5 = strIngredient5,
        strIngredient6 = strIngredient6,
        strIngredient7 = strIngredient7,
        strIngredient8 = strIngredient8,
        strIngredient9 = strIngredient9,
        strIngredient10 = strIngredient10,
        strIngredient11 = strIngredient11,
        strIngredient12 = strIngredient12,
        strIngredient13 = strIngredient13,
        strIngredient14 = strIngredient14,
        strIngredient15 = strIngredient15,
        strMeasure1 = strMeasure1,
        strMeasure2 = strMeasure2,
        strMeasure3 = strMeasure3,
        strMeasure4 = strMeasure4,
        strMeasure5 = strMeasure5,
        strMeasure6 = strMeasure6,
        strMeasure7 = strMeasure7,
        strMeasure8 = strMeasure8,
        strMeasure9 = strMeasure9,
        strMeasure10 = strMeasure10,
        strMeasure11 = strMeasure11,
        strMeasure12 = strMeasure12,
        strMeasure13 = strMeasure13,
        strMeasure14 = strMeasure14,
        strMeasure15 = strMeasure15,
    )
}


internal fun CocktailDTO.toCocktailDBO(): CocktailDBO {
    return CocktailDBO(
        id = id,
        name = name,
        category = category,
        alcoholic = alcoholic,
        videoLink = videoLink,
        tags = tags,
        glass = glass,
        instructions = instructions,
        drinkImage = drinkImage,
        strIngredient1 = strIngredient1,
        strIngredient2 = strIngredient2,
        strIngredient3 = strIngredient3,
        strIngredient4 = strIngredient4,
        strIngredient5 = strIngredient5,
        strIngredient6 = strIngredient6,
        strIngredient7 = strIngredient7,
        strIngredient8 = strIngredient8,
        strIngredient9 = strIngredient9,
        strIngredient10 = strIngredient10,
        strIngredient11 = strIngredient11,
        strIngredient12 = strIngredient12,
        strIngredient13 = strIngredient13,
        strIngredient14 = strIngredient14,
        strIngredient15 = strIngredient15,
        strMeasure1 = strMeasure1,
        strMeasure2 = strMeasure2,
        strMeasure3 = strMeasure3,
        strMeasure4 = strMeasure4,
        strMeasure5 = strMeasure5,
        strMeasure6 = strMeasure6,
        strMeasure7 = strMeasure7,
        strMeasure8 = strMeasure8,
        strMeasure9 = strMeasure9,
        strMeasure10 = strMeasure10,
        strMeasure11 = strMeasure11,
        strMeasure12 = strMeasure12,
        strMeasure13 = strMeasure13,
        strMeasure14 = strMeasure14,
        strMeasure15 = strMeasure15,
    )
}

internal fun IngredientDTO.toIngredientDBO(): IngredientDBO {
    return IngredientDBO(
        ingredientName = ingredientName
    )
}

internal fun IngredientDTO.toIngredient(): Ingredient {
    return Ingredient(
        ingredientName = ingredientName
    )
}
internal fun IngredientDBO.toIngredient(): Ingredient {
    return Ingredient(
        ingredientName = ingredientName
    )
}
internal fun IngredientDetailedDTO.toIngredientDetailedDBO(): IngredientDetailedDBO {
    return IngredientDetailedDBO(
        id = id,
        name = name,
        description = description,
        drinkType = drinkType,
        isAlcoholic = isAlcoholic,
        alcoholicVolume = alcoholicVolume
    )
}
internal fun IngredientDetailedDTO.toIngredientDetailed(): IngredientDetailed {
    return IngredientDetailed(
        id = id,
        name = name,
        description = description,
        drinkType = drinkType,
        isAlcoholic = isAlcoholic,
        alcoholicVolume = alcoholicVolume
    )
}
internal fun IngredientDetailedDBO.toIngredientDetailed(): IngredientDetailed {
    return IngredientDetailed(
        id = id,
        name = name,
        description = description,
        drinkType = drinkType,
        isAlcoholic = isAlcoholic,
        alcoholicVolume = alcoholicVolume
    )
}

