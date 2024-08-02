import SwiftUI
import Shared

struct ContentView: View {
    @StateObject
    var controller = Controller()
    
    var body: some View {
        ScrollView {
            LazyVGrid(columns: [GridItem(), GridItem()], content: {
                ForEach(controller.characters, id: \.id) { character in
                    CharacterView(character: character)
                }
            })
            .task {
                await controller.startObserving()
            }
        }
    }
}

class Controller: ObservableObject {
    @Published
    var characters: [CharacterModel] = []
    
    @MainActor
    func startObserving() async {
        CharacterController().loadNewCharacters()
        
        for await newList in CharacterController().characters {
            characters = newList
        }
    }
}

struct CharacterView: View {
    let character: CharacterModel
    
    var body: some View {
        VStack {
            AsyncImage(
                url: URL(string: character.image),
                content: { image in
                    image.resizable()
                        .aspectRatio(contentMode: .fit)
                },
                placeholder: {
                    ProgressView()
                }
                
            )
            Text(character.name)
                .font(.system(size: 24))
                .lineLimit(1)
            CharacterDescriptionValue(description: "status: ", value: character.status)
            CharacterDescriptionValue(description: "species: ", value: character.species)
            CharacterDescriptionValue(description: "type: ", value: character.type)
            CharacterDescriptionValue(description: "gender: ", value: character.gender)
        }
        .background(Color.gray.opacity(0.3))
        .cornerRadius(16)
    }
    
    init(character: CharacterModel) {
        self.character = character
    }
}

struct CharacterDescriptionValue: View {
    let description: String
    let value: String
    
    var body: some View {
        if (!value.isEmpty) {
            HStack() {
                Text(description)
                    .frame(maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/)
                Text(value)
                    .frame(maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/)
            }
        }
    }
    
    init(description: String, value: String) {
        self.description = description
        self.value = value
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
